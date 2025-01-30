package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.PaymentMethodController;
import com.philippo.algafood.api.model.PaymentMethodModel;
import com.philippo.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public PaymentMethodModelAssembler() {
        super(PaymentMethodController.class, PaymentMethodModel.class);
    }

    @Override
    public PaymentMethodModel toModel(PaymentMethod paymentMethod){
        PaymentMethodModel paymentMethodModel = createModelWithId(paymentMethod.getId(), paymentMethod);
        modelmapper.map(paymentMethod, paymentMethodModel);

        if (algaSecurity.canConsultPaymentMethods()) {
            paymentMethodModel.add(algaLinks.linkToPaymentMethods("paymentMethods"));
        }

        return paymentMethodModel;
    }

    @Override
    public CollectionModel<PaymentMethodModel> toCollectionModel(Iterable<? extends PaymentMethod> entities) {
        CollectionModel<PaymentMethodModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultPaymentMethods()) {
            collectionModel.add(algaLinks.linkToPaymentMethods());
        }

        return collectionModel;
    }
}
