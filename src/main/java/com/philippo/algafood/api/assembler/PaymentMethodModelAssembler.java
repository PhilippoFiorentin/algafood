package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.PaymentMethodModel;
import com.philippo.algafood.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodModelAssembler {
    @Autowired
    private ModelMapper modelmapper;

    public PaymentMethodModel toModel(PaymentMethod paymentMethod){
        return modelmapper.map(paymentMethod,PaymentMethodModel.class);
    }

    public List<PaymentMethodModel> toCollectionModel(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(paymentMethod -> toModel(paymentMethod))
                .collect(Collectors.toList());
    }
}
