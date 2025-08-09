package com.chefsupport.api.service;

import com.chefsupport.api.domain.Recipe;
import com.chefsupport.api.domain.RecipeIngredient;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class NutritionCalculator {

  public BigDecimal estimateKcalPerServing(Recipe recipe) {
    if (recipe.getServings() <= 0) {
      return BigDecimal.ZERO;
    }
    BigDecimal totalKcal = BigDecimal.ZERO;
    for (RecipeIngredient ing : recipe.getIngredients()) {
      totalKcal = totalKcal.add(estimateIngredientKcal(ing));
    }
    return totalKcal.divide(BigDecimal.valueOf(recipe.getServings()), 2, RoundingMode.HALF_UP);
  }

  private BigDecimal estimateIngredientKcal(RecipeIngredient ing) {
    // Heuristic placeholder: if quantity present and unit grams/ml, use kcal density heuristic.
    if (ing.getQuantity() == null || ing.getQuantity().compareTo(BigDecimal.ZERO) <= 0) {
      return BigDecimal.ZERO;
    }
    String unit = ing.getUnit() == null ? "" : ing.getUnit().toLowerCase();
    BigDecimal qty = ing.getQuantity();
    BigDecimal kcalPer100 = heuristicKcalPer100g(ing.getName());
    if (unit.equals("g") || unit.equals("gram") || unit.equals("grams")) {
      return qty.multiply(kcalPer100).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
    if (unit.equals("ml")) {
      return qty.multiply(kcalPer100).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
    // Fallback: assume quantity is count ~ 1 serving ~ 150 kcal heuristic
    return BigDecimal.valueOf(150);
  }

  private BigDecimal heuristicKcalPer100g(String name) {
    String n = name.toLowerCase();
    if (n.contains("oil") || n.contains("butter")) return BigDecimal.valueOf(720);
    if (n.contains("rice") || n.contains("pasta")) return BigDecimal.valueOf(350);
    if (n.contains("chicken") || n.contains("beef")) return BigDecimal.valueOf(200);
    if (n.contains("bread")) return BigDecimal.valueOf(260);
    if (n.contains("milk")) return BigDecimal.valueOf(60);
    if (n.contains("cheese")) return BigDecimal.valueOf(400);
    if (n.contains("egg")) return BigDecimal.valueOf(155);
    if (n.contains("banana") || n.contains("fruit")) return BigDecimal.valueOf(90);
    if (n.contains("vege") || n.contains("salad") || n.contains("tomato") || n.contains("cucumber")) return BigDecimal.valueOf(30);
    return BigDecimal.valueOf(150);
  }
}

