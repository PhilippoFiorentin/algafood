package com.philippo.algafood.domain.infrastructure.service.storage;

import com.philippo.algafood.domain.service.PhotoStorageService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {
    @Override
    public void store(NewPhoto newPhoto) {

    }

    @Override
    public void delete(String fileName) {

    }

    @Override
    public InputStream recover(String fileName) {
        return null;
    }
}
