package com.philippo.algafood.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.philippo.algafood.domain.infrastructure.service.storage.LocalPhotoStorageService;
import com.philippo.algafood.domain.infrastructure.service.storage.S3PhotoStorageService;
import com.philippo.algafood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
                storageProperties.getS3().getAccessKeyId(),
                storageProperties.getS3().getSecretAccessKeyId());

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }

    @Bean
    public PhotoStorageService photoStorageService() {
        if (StorageProperties.TypeStorage.S3.equals(storageProperties.getType())) {
            return new S3PhotoStorageService();
        } else{
            return new LocalPhotoStorageService();
        }
    }
}
