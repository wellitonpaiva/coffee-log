package com.thescientist.coffeelog;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;

class CoffeeLogControllerTest {

    @Test
    void addCoffee() {
        CoffeeLogRepo service = Mockito.mock(CoffeeLogRepo.class);
        Recipe expected = Recipe.builder()
                .coffee(BigDecimal.ONE)
                .water(BigDecimal.ONE)
                .build();

        Mockito.when(service.add(any())).thenReturn(expected);

        CoffeeLogController sut = new CoffeeLogController(service);
        Recipe recipe = (Recipe) sut.addCoffee(expected).getBody();
        assertThat(recipe, is(expected));
    }
}