package com.thescientist.coffeelog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private int id;
    private BigDecimal coffee;
    private BigDecimal water;
    private String method;
    private String notes;

}
