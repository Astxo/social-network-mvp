package com.easydmarc.socialnetworkmvp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AvatarValidator implements Validator {
    private static final long MAX_AVATAR_SIZE_BYTES = 1024 * 1024; // 1 MB maximum size

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile avatarFile = (MultipartFile) target;

        if (avatarFile.isEmpty()) {
            errors.rejectValue("avatarPath", "avatar.required", "Avatar file shouldn't be null");
            return;
        }

        // TODO validate file format

        if (avatarFile.getSize() > MAX_AVATAR_SIZE_BYTES) {
            errors.rejectValue("avatarPath", "avatar.size", "Avatar size exceeds the maximum allowed size.");
        }

    }
}
