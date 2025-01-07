package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.AlgaLinks;
import com.philippo.algafood.api.v1.controller.PaymentMethodController;
import com.philippo.algafood.api.v1.model.PaymentMethodModel;
import com.philippo.algafood.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodModelAssembler extends RepresentationModelAssemblerSupport<PaymentMethod, PaymentMethodModel> {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PaymentMethodModelAssembler() {
        super(PaymentMethodController.class, PaymentMethodModel.class);
    }

    @Override
    public PaymentMethodModel toModel(PaymentMethod paymentMethod){
        PaymentMethodModel paymentMethodModel = createModelWithId(paymentMethod.getId(), paymentMethod);
        modelmapper.map(paymentMethod, paymentMethodModel);

        paymentMethodModel.add(algaLinks.linkToPaymentMethods("paymentMethods"));

        return paymentMethodModel;
    }

    @Override
    public CollectionModel<PaymentMethodModel> toCollectionModel(Iterable<? extends PaymentMethod> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToPaymentMethods());
    }
}
