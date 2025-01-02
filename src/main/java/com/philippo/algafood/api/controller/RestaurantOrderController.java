package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.RestaurantOrderInputDisassembler;
import com.philippo.algafood.api.assembler.RestaurantOrderModelAssembler;
import com.philippo.algafood.api.assembler.RestaurantOrderSummaryAssembler;
import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.api.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.api.model.input.RestaurantOrderInput;
import com.philippo.algafood.api.openapi.controller.RestaurantOrderControllerOpenApi;
import com.philippo.algafood.core.data.PageWrapper;
import com.philippo.algafood.core.data.PageableTranslator;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.filter.OrderFilter;
import com.philippo.algafood.domain.infrastructure.repository.spec.OrderSpecs;
import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.repository.RestaurantOrderRepository;
import com.philippo.algafood.domain.service.OrderIssuanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantOrderController implements RestaurantOrderControllerOpenApi {

    @Autowired
    private RestaurantOrderRepository restaurantOrderRepository;

    @Autowired
    private OrderIssuanceService orderIssuanceService;

    @Autowired
    private RestaurantOrderModelAssembler restaurantOrderModelAssembler;

    @Autowired
    private RestaurantOrderSummaryAssembler restaurantOrderSummaryAssembler;

    @Autowired
    private RestaurantOrderInputDisassembler restaurantOrderInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<RestaurantOrder> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<RestaurantOrderSummaryModel> search(@PageableDefault(size = 10) Pageable pageable, OrderFilter filter) {

        pageable = translatePageable(pageable);

        Page<RestaurantOrder> orderPages = restaurantOrderRepository.findAll(OrderSpecs.usingFilter(filter), pageable);

        orderPages = new PageWrapper<>(orderPages, pageable);

        return pagedResourcesAssembler.toModel(orderPages, restaurantOrderSummaryAssembler);
    }

    @GetMapping("/{orderUuid}")
    public RestaurantOrderModel find(@PathVariable String orderUuid) {
        RestaurantOrder restaurantOrder = orderIssuanceService.findOrFail(orderUuid);
        return restaurantOrderModelAssembler.toModel(restaurantOrder);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantOrderModel add(@Valid @RequestBody RestaurantOrderInput orderInput) {

        try {
            RestaurantOrder newOrder = restaurantOrderInputDisassembler.toDomainObject(orderInput);

            newOrder.setClient(new User());
            newOrder.getClient().setId(1L);

            newOrder = orderIssuanceService.issue(newOrder);

            return restaurantOrderModelAssembler.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private Pageable translatePageable(Pageable apiPageable){
        var mapping = Map.of(
                "uuid", "uuid",
                "subtotal", "subtotal",
                "deliveryFee", "deliveryFee",
                "total", "total",
                "status", "status",
                "creationDate", "creationDate",
                "restaurant.name", "restaurant.name",
                "clientName", "client.name"
        );

        return PageableTranslator.translate(apiPageable, mapping);
    }

}
