package com.philippo.algafood.domain.infrastructure.service.storage;

import com.philippo.algafood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
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
        } catch (IOException e) {
            throw new StorageException("The file could not be saved. " +
                    "Please try again later or check the file format and size.", e);
        }

    }

    private Path getFilePath(String filename){
        return storageLocation.resolve(Path.of(filename));
    }
}
