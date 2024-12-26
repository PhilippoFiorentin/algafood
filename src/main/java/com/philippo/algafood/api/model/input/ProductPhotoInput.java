package com.philippo.algafood.api.model.input;

import com.philippo.algafood.core.validation.FileContentType;
import com.philippo.algafood.core.validation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductPhotoInput {

    @ApiModelProperty(required = true)
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;

    @ApiModelProperty(value = "Product photo description", required = true)
    @NotBlank
    private String description;
}
