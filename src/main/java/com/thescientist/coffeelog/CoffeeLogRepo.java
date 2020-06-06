package com.thescientist.coffeelog;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeLogRepo {

    private static final List<Recipe> coffees = new ArrayList<>();

    public Recipe add(Recipe recipe) {
        int id = coffees.size();
        recipe.setId(id);
        coffees.add(recipe);
        return coffees.get(id);
    }

    public List<Recipe> getAll() {
        return coffees;
    }
}
