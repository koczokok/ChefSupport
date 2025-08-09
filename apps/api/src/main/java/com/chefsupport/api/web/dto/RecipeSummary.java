package com.chefsupport.api.web.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class RecipeSummary {
  private UUID id;
  private String name;
  private int servings;
  private BigDecimal kcalPerServing;
  private List<String> tags;

  public RecipeSummary() {}

  public RecipeSummary(UUID id, String name, int servings, BigDecimal kcalPerServing, List<String> tags) {
    this.id = id; this.name = name; this.servings = servings; this.kcalPerServing = kcalPerServing; this.tags = tags;
  }

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public int getServings() { return servings; }
  public void setServings(int servings) { this.servings = servings; }
  public BigDecimal getKcalPerServing() { return kcalPerServing; }
  public void setKcalPerServing(BigDecimal kcalPerServing) { this.kcalPerServing = kcalPerServing; }
  public List<String> getTags() { return tags; }
  public void setTags(List<String> tags) { this.tags = tags; }
}

