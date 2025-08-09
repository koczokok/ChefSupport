package com.chefsupport.api.web.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RecipeCreate {
  @NotBlank
  private String name;
  @Min(1)
  private int servings;
  private Integer prepMinutes;
  private Integer cookMinutes;
  private List<String> tags;
  @Valid
  private List<IngredientDto> ingredients;
  private String instructionsMd;

  public RecipeCreate() {}

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
  public List<IngredientDto> getIngredients() { return ingredients; }
  public void setIngredients(List<IngredientDto> ingredients) { this.ingredients = ingredients; }
  public String getInstructionsMd() { return instructionsMd; }
  public void setInstructionsMd(String instructionsMd) { this.instructionsMd = instructionsMd; }
}

