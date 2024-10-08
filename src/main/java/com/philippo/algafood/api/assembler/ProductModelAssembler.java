package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.ProductModel;
import com.philippo.algafood.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProductModel toModel(Product product) {
        return modelMapper.map(product, ProductModel.class);
    }

    public List<ProductModel> toCollectionModel(List<Product> products) {
        return products.stream()
                .map(product -> toModel(product))
                .collect(Collectors.toList());
    }
}
