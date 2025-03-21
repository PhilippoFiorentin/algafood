package com.philippo.algafood;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.service.RegisterKitchenService;
import jakarta.validation.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenRegisterIntegrationT {

    @Autowired
    RegisterKitchenService registerKitchen;

    @Autowired
    KitchenRepository kitchenRepository;

    @Test
    public void shouldAssignId_WhenRegisterKitchenWithRightData(){
        //case
        Kitchen newKitchen = new Kitchen();
        newKitchen.setName("Chinese");

        //action
        newKitchen = registerKitchen.save(newKitchen);

        //validation
        assertThat(newKitchen).isNotNull();
        assertThat(newKitchen.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFail_WhenRegisterKitchenWithNoName(){
        Kitchen newKitchen = new Kitchen();
        newKitchen.setName(null);

        registerKitchen.save(newKitchen);
    }

    @Test(expected = EntityInUseException.class)
    public void shouldFail_WhenDeleteKitchenInUse(){
        registerKitchen.delete(1L);
    }

    @Test(expected = KitchenNotFoundException.class)
    public void shouldFail_WhenDeleteKitchenNoExists(){
        registerKitchen.delete(100L);
    }
}
