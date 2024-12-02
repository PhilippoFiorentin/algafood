package com.philippo.algafood.domain.infrastructure.service.storage;

import com.philippo.algafood.core.storage.StorageProperties;
import com.philippo.algafood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class LocalPhotoStorageService implements PhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

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
    public RecoveredPhoto recover(String fileName) {
        try {
            Path filePath = getFilePath(fileName);
            RecoveredPhoto recoveredPhoto = RecoveredPhoto
                    .builder()
                    .inputStream(Files.newInputStream(filePath)).build();
            return recoveredPhoto;
        } catch (Exception e) {
            throw new StorageException("The file could not be recovered.", e);
        }
    }

    private Path getFilePath(String filename){
        return storageProperties.getLocal().getPhotoDirectory().resolve(Path.of(filename));
    }
}
