package com.philippo.algafood.api.v1.openapi.model;

import com.philippo.algafood.api.v1.model.ProductModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ProductsModel")
@Data
public class ProductsModelOpenApi {

    private EmbeddedProductModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedProductsModel")
    @Data
    public class EmbeddedProductModelOpenApi {
        private List<ProductModel> products;
    }
}
