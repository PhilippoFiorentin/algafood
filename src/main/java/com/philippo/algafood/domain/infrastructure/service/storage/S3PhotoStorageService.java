package com.philippo.algafood.domain.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.philippo.algafood.core.storage.StorageProperties;
import com.philippo.algafood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private AmazonS3 amazonS3;

    @Override
    public void store(NewPhoto newPhoto) {
        try{
            String filePath = getFilePath(newPhoto.getFilename());

            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    filePath,
                    newPhoto.getInputStream(),
                    objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Unable to upload file to Amazon S3", e);
        }
    }

    private String getFilePath(String filename) {
        return String.format("%s/%s", storageProperties.getS3().getPhotoDirectory(), filename);
    }

    @Override
    public void delete(String fileName) {
        try{
            String filePath = getFilePath(fileName);

            var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), filePath);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Unable to delete file on Amazon S3", e);
        }

    }

    @Override
    public InputStream recover(String fileName) {
        return null;
    }
}
