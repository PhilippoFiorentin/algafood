package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.PaymentMethodNotFoundException;
import com.philippo.algafood.domain.model.PaymentMethod;
import com.philippo.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterPaymentMethodService {

    public static final String PAYMENT_METHOD_IN_USE_MESSAGE = "The payment method with code %d cannot be deleted because it is in use";

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod save(PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    public void delete(Long paymentMethodId){
        try{
            paymentMethodRepository.deleteById(paymentMethodId);
            paymentMethodRepository.flush();
        } catch (EmptyResultDataAccessException e){
            throw new PaymentMethodNotFoundException(paymentMethodId);
        } catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format(PAYMENT_METHOD_IN_USE_MESSAGE));
        }
    }

    public PaymentMethod findOrFail(Long paymentMethodId){
        return paymentMethodRepository
                .findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
    }
}
