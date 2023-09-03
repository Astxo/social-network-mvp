package com.easydmarc.socialnetworkmvp.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GCSProperties {
    @Value("${gcs.projectId}")
    private String projectId;

    @Value("${gcs.bucketName}")
    private String bucketName;

    @Value("${gcs.credentialsPath}")
    private String credentialsPath;

    public String getProjectId() {
        return projectId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getCredentialsPath() {
        return credentialsPath;
    }
}
