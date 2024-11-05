package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.RestaurantOrderInputDisassembler;
import com.philippo.algafood.api.assembler.RestaurantOrderModelAssembler;
import com.philippo.algafood.api.assembler.RestaurantOrderSummaryAssembler;
import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.api.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.api.model.input.RestaurantOrderInput;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.infrastructure.repository.spec.OrderSpecs;
import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.repository.RestaurantOrderRepository;
import com.philippo.algafood.domain.repository.filter.OrderFilter;
import com.philippo.algafood.domain.service.OrderIssuanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class RestaurantOrderController {

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

    @GetMapping
    public List<RestaurantOrderSummaryModel> search(OrderFilter filter) {
        List<RestaurantOrder> allRestaurantOrders = restaurantOrderRepository.findAll(OrderSpecs.usingFilter(filter));
        return restaurantOrderSummaryAssembler.toCollectionModel(allRestaurantOrders);
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
}
