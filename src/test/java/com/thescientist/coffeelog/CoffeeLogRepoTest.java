package com.thescientist.coffeelog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

class CoffeeLogRepoTest {

    @Test
    void add() {
        CoffeeLogRepo sut = new CoffeeLogRepo();
        Recipe result = sut.add(Recipe.builder()
                .method("Aeropress")
                .coffee(BigDecimal.ONE)
                .water(BigDecimal.ZERO)
                .build());
        assertThat(result, is(Recipe.builder()
                .id(1)
                .coffee(BigDecimal.ONE)
                .water(BigDecimal.ZERO)
                .method("Aeropress")
                .build()));
    }

    @Test
    void getAllWithZeroInput() {
        CoffeeLogRepo sut = new CoffeeLogRepo();
        List<Recipe> recipes = sut.getAll();
        assertThat(recipes, hasSize(0));
    }

    @Test
    void getAllWithOneEntry() {
        CoffeeLogRepo sut = new CoffeeLogRepo();
        Recipe recipe = Recipe.builder()
                .method("Aeropress")
                .coffee(BigDecimal.ONE)
                .water(BigDecimal.ONE)
                .build();
        sut.add(recipe);
        List<Recipe> recipes = sut.getAll();
        assertThat(recipes, hasSize(1));
        assertThat(recipes, contains(recipe));
    }
}