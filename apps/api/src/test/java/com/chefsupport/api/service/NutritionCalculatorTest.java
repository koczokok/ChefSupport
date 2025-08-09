package com.chefsupport.api.service;

import com.chefsupport.api.domain.Recipe;
import com.chefsupport.api.domain.RecipeIngredient;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NutritionCalculatorTest {

  @Test
  void calculatesKcalPerServingFromIngredients() {
    Recipe recipe = new Recipe();
    recipe.setName("Test");
    recipe.setServings(2);

    RecipeIngredient rice = new RecipeIngredient();
    rice.setName("rice");
    rice.setUnit("g");
    rice.setQuantity(BigDecimal.valueOf(200)); // 200g rice ~ 700 kcal

    RecipeIngredient chicken = new RecipeIngredient();
    chicken.setName("chicken breast");
    chicken.setUnit("g");
    chicken.setQuantity(BigDecimal.valueOf(300)); // ~ 600 kcal

    recipe.setIngredients(List.of(rice, chicken));

    NutritionCalculator calc = new NutritionCalculator();
    BigDecimal perServing = calc.estimateKcalPerServing(recipe);

    // Heuristic: rice 200g @ 350/100g = 700, chicken 300g @ 200/100g = 600, total 1300 / 2 = 650
    assertEquals(BigDecimal.valueOf(650.00).setScale(2), perServing);
  }
}

