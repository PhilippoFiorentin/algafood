package com.philippo.algafood.api.V1.model.input;

import com.philippo.algafood.core.validation.FileContentType;
import com.philippo.algafood.core.validation.FileSize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class ProductPhotoInput {

    @Schema(description = "Product photo file (max 500KB, JPG and PNG only)")
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;

    @Schema(description = "Product photo description")
    @NotBlank
    private String description;
}
