package com.philippo.algafood.core.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();

    @Getter
    @Setter
    public class Local{

        private Path photoDirectory;

    }

    @Getter
    @Setter
    public class S3{

        private String accessKeyId;
        private String secretAccessKeyId;
        private String bucket;
        private String region;
        private String photoDirectory;

    }
}
