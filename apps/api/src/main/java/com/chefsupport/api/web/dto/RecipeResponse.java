package com.chefsupport.api.web.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class RecipeResponse {
  private UUID id;
  private String name;
  private int servings;
  private Integer prepMinutes;
  private Integer cookMinutes;
  private List<String> tags;
  private BigDecimal kcalPerServing;
  private List<IngredientDto> ingredients;
  private String instructionsMd;

  public RecipeResponse() {}

  public RecipeResponse(UUID id, String name, int servings, Integer prepMinutes, Integer cookMinutes, List<String> tags, BigDecimal kcalPerServing, List<IngredientDto> ingredients, String instructionsMd) {
    this.id = id; this.name = name; this.servings = servings; this.prepMinutes = prepMinutes; this.cookMinutes = cookMinutes; this.tags = tags; this.kcalPerServing = kcalPerServing; this.ingredients = ingredients; this.instructionsMd = instructionsMd;
  }

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public int getServings() { return servings; }
  public void setServings(int servings) { this.servings = servings; }
  public Integer getPrepMinutes() { return prepMinutes; }
  public void setPrepMinutes(Integer prepMinutes) { this.prepMinutes = prepMinutes; }
  public Integer getCookMinutes() { return cookMinutes; }
  public void setCookMinutes(Integer cookMinutes) { this.cookMinutes = cookMinutes; }
  public List<String> getTags() { return tags; }
  public void setTags(List<String> tags) { this.tags = tags; }
  public BigDecimal getKcalPerServing() { return kcalPerServing; }
  public void setKcalPerServing(BigDecimal kcalPerServing) { this.kcalPerServing = kcalPerServing; }
  public List<IngredientDto> getIngredients() { return ingredients; }
  public void setIngredients(List<IngredientDto> ingredients) { this.ingredients = ingredients; }
  public String getInstructionsMd() { return instructionsMd; }
  public void setInstructionsMd(String instructionsMd) { this.instructionsMd = instructionsMd; }
}

