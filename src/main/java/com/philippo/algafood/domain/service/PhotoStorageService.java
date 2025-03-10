package com.philippo.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

    void store(NewPhoto newPhoto);

    void delete(String fileName);

    RecoveredPhoto recover(String fileName);

    default void substitute(String oldFilename, NewPhoto newPhoto) {
        this.store(newPhoto);

        if (oldFilename != null) {
            this.delete(oldFilename);
        }
    }

    default String generateFilename(String originalName) {
        return UUID.randomUUID() + "_" + originalName;
    }

    @Builder
    @Getter
    class NewPhoto {

        private String filename;
        private String contentType;
        private InputStream inputStream;
    }

    @Builder
    @Getter
    class RecoveredPhoto {
        private InputStream inputStream;
        private String url;

        public boolean hasUrl(){
            return url != null;
        }

        public boolean hasInputStream(){
            return inputStream != null;
        }
    }
}
