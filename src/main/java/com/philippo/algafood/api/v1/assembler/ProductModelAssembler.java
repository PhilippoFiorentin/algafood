package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.AlgaLinks;
import com.philippo.algafood.api.v1.controller.RestaurantProductController;
import com.philippo.algafood.api.v1.model.ProductModel;
import com.philippo.algafood.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public ProductModelAssembler() {
        super(RestaurantProductController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(Product product) {
        ProductModel productModel = createModelWithId(product.getId(), product, product.getRestaurant().getId());
        modelMapper.map(product, productModel);

        productModel.add(algaLinks.linkToProducts(product.getRestaurant().getId(), "products"));

        productModel.add(algaLinks.linkToProductPhoto(product.getRestaurant().getId(), product.getId(), "photo"));

        return productModel;
    }

    @Override
    public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends Product> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToProducts());
    }
}
