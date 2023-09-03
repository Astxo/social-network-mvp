package com.easydmarc.socialnetworkmvp.controller;

import com.easydmarc.socialnetworkmvp.dto.*;
import com.easydmarc.socialnetworkmvp.model.User;
import com.easydmarc.socialnetworkmvp.service.AuthenticationService;
import com.easydmarc.socialnetworkmvp.service.UserService;
import com.easydmarc.socialnetworkmvp.service.impl.GCSProperties;
import com.easydmarc.socialnetworkmvp.service.impl.GCSStorageService;
import com.easydmarc.socialnetworkmvp.validator.AvatarValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    private final GCSStorageService gcsStorageService;

    private  final GCSProperties gcsProperties;

    private final AvatarValidator avatarValidator;

    @Autowired
    public UserController(AuthenticationService authenticationService,
                          UserService userService,
                          GCSStorageService gcsStorageService,
                          GCSProperties gcsProperties,
                          AvatarValidator avatarValidator) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.gcsStorageService = gcsStorageService;
        this.gcsProperties = gcsProperties;
        this.avatarValidator = avatarValidator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/getUsersByCriteria")
    public List<User> getByCriteria(@RequestBody CriteriaDto criteriaDto) {
        return userService.loadAllByCriteria(criteriaDto);
    }

    @PostMapping("/register") // TODO Add Authorization check
    public ResultDto register(@RequestBody UserCreationDto userCreationDto) {
        userService.register(userCreationDto);
        return new ResultDto(true, List.of());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @NonNull AuthDto loginDto) {
        var token = authenticationService.login(loginDto);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/{id}") // TODO Add Authorization check
    public ResultDto updateUser(@PathVariable @NonNull Integer id,
                                @ModelAttribute("user") @NonNull User user,
                                @RequestParam("avatarFile") @Valid @NonNull MultipartFile avatarFile,
                                BindingResult bindingResult) throws IOException {

        validate(id, user, avatarFile, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResultDto(false, bindingResult.getAllErrors()
                        .stream()
                        .map(ObjectError::toString)
                        .collect(Collectors.toList()));
        }

        if (!avatarFile.isEmpty()) {

            String fileName = id + "-avatar-id";
            gcsStorageService.uploadAvatar(avatarFile, fileName);

            String avatarUrl = getStorageUrl() + "/" + fileName;
            user.setAvatarPath(avatarUrl);
        }

        userService.update(user);

        return new ResultDto(true, List.of());

    }

    private void validate(Integer id, User user, MultipartFile avatarFile, BindingResult bindingResult) {
        avatarValidator.validate(avatarFile, bindingResult);
        if (!Objects.equals(id, user.getId())) {
            bindingResult.rejectValue("id", "update.denied");
        }
    }

    private String getStorageUrl() {
        return "https://storage.googleapis.com/" + gcsProperties.getBucketName();
    }

}
