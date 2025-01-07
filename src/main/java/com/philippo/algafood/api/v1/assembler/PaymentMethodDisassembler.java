package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.model.input.PaymentMethodInput;
import com.philippo.algafood.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentMethod toDomainObject(PaymentMethodInput paymentMethodInput){
        return modelMapper.map(paymentMethodInput, PaymentMethod.class);
    }

    public void copyToDomainObject(PaymentMethodInput paymentMethodInput, PaymentMethod paymentMethod){
        modelMapper.map(paymentMethodInput, paymentMethod);
    }
}
