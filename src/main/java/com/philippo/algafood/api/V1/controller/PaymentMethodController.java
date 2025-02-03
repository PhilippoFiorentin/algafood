package com.philippo.algafood.api.V1.controller;

import com.philippo.algafood.api.V1.assembler.PaymentMethodDisassembler;
import com.philippo.algafood.api.V1.assembler.PaymentMethodModelAssembler;
import com.philippo.algafood.api.V1.model.PaymentMethodModel;
import com.philippo.algafood.api.V1.model.input.PaymentMethodInput;
import com.philippo.algafood.api.V1.openapi.controller.PaymentMethodControllerOpenApi;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.model.PaymentMethod;
import com.philippo.algafood.domain.repository.PaymentMethodRepository;
import com.philippo.algafood.domain.service.RegisterPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/v1/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodController implements PaymentMethodControllerOpenApi {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private RegisterPaymentMethodService registerPaymentMethod;

    @Autowired
    private PaymentMethodModelAssembler paymentMethodModelAssembler;

    @Autowired
    private PaymentMethodDisassembler paymentMethodDisassembler;

    @CheckSecurity.PaymentMethods.CanConsult
    @GetMapping
    public ResponseEntity<CollectionModel<PaymentMethodModel>> list(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime lastDateUpdated = paymentMethodRepository.getLastDateUpdated();

        if (lastDateUpdated != null) {
            eTag = String.valueOf(lastDateUpdated.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        CollectionModel<PaymentMethodModel> paymentMethodModels = paymentMethodModelAssembler
                .toCollectionModel(paymentMethodRepository.findAll());

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(paymentMethodModels);
    }

    @CheckSecurity.PaymentMethods.CanConsult
    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodModel> find(@PathVariable Long paymentMethodId, ServletWebRequest request){
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime lastDateUpdated = paymentMethodRepository.getLastDateUpdatedById(paymentMethodId);

        if (lastDateUpdated != null) {
            eTag = String.valueOf(lastDateUpdated.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        PaymentMethod paymentMethod = registerPaymentMethod.findOrFail(paymentMethodId);
        PaymentMethodModel paymentMethodModel = paymentMethodModelAssembler.toModel(paymentMethod);

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(paymentMethodModel);
    }

    @CheckSecurity.PaymentMethods.CanEdit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodModel add(@RequestBody @Valid PaymentMethodInput paymentMethodInput){
        PaymentMethod paymentMethod = paymentMethodDisassembler.toDomainObject(paymentMethodInput);
        return paymentMethodModelAssembler.toModel(registerPaymentMethod.save(paymentMethod));
    }

    @CheckSecurity.PaymentMethods.CanEdit
    @PutMapping("/{paymentMethodId}")
    public PaymentMethodModel update(@PathVariable Long paymentMethodId, @RequestBody @Valid PaymentMethodInput paymentMethodInput){
        PaymentMethod currentPaymentMethod = registerPaymentMethod.findOrFail(paymentMethodId);
        paymentMethodDisassembler.copyToDomainObject(paymentMethodInput, currentPaymentMethod);
        currentPaymentMethod = registerPaymentMethod.save(currentPaymentMethod);
        return paymentMethodModelAssembler.toModel(currentPaymentMethod);

    }

    @CheckSecurity.PaymentMethods.CanEdit
    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long paymentMethodId){
        registerPaymentMethod.delete(paymentMethodId);
    }
}
