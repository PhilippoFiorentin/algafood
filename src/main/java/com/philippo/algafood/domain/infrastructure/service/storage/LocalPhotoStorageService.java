package com.philippo.algafood.domain.infrastructure.service.storage;

import com.philippo.algafood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageService implements PhotoStorageService {

    @Value("${algafood.storage.local.photo.directory}")
    private Path storageLocation;

    @Override
    public void store(NewPhoto newPhoto) {

        try {
            Path filePath = getFilePath(newPhoto.getFilename());
            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("The file could not be saved. " +
                    "Please try again later or check the file format and size.", e);
        }

    }

    @Override
    public void delete(String fileName) {

        try {
            Path filePath = getFilePath(fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("The file could not be deleted.", e);
        }
    }

    @Override
    public InputStream recover(String fileName) {
        try {
            Path filePath = getFilePath(fileName);
            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new StorageException("The file could not be recovered.", e);
        }
    }

    private Path getFilePath(String filename){
        return storageLocation.resolve(Path.of(filename));
    }
}
