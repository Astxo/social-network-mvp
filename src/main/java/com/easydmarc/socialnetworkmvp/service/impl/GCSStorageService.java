package com.easydmarc.socialnetworkmvp.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class GCSStorageService {

    private  final GCSProperties gcsProperties;

    public GCSStorageService(GCSProperties gcsProperties) {
        this.gcsProperties = gcsProperties;
    }

    public void uploadAvatar(MultipartFile file, String fileName) throws IOException {
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(Objects.requireNonNull(getCredentialsStream())))
                .setProjectId(gcsProperties.getProjectId())
                .build()
                .getService();

        Bucket bucket = storage.get(gcsProperties.getBucketName());

        BlobId blobId = BlobId.of(gcsProperties.getBucketName(), fileName);

        bucket.create(String.valueOf(blobId), file.getBytes(), file.getContentType());
    }


    private InputStream getCredentialsStream() {
        return getClass().getResourceAsStream(gcsProperties.getCredentialsPath());
    }
}
