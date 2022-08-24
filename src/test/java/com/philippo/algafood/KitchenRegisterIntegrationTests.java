package com.philippo.algafood;

import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.service.RegisterKitchenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenRegisterIntegrationTests {

    @Autowired
    RegisterKitchenService registerKitchen;

    @Test
    public void testKitchenRegisterSuccessfully(){
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
    public void testKitchenRegisterWhenHasNoName(){
        Kitchen newKitchen = new Kitchen();
        newKitchen.setName(null);

        registerKitchen.save(newKitchen);
    }
}
