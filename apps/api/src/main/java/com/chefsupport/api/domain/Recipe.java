package com.chefsupport.api.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recipe")
public class Recipe {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int servings;

  private Integer prepMinutes;

  private Integer cookMinutes;

  @ElementCollection
  @CollectionTable(name = "recipe_tags", joinColumns = @JoinColumn(name = "recipe_id"))
  @Column(name = "tag")
  private List<String> tags = new ArrayList<>();

  private BigDecimal kcalPerServing;

  @Column(columnDefinition = "text")
  private String instructionsMd;

  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RecipeIngredient> ingredients = new ArrayList<>();

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
  public String getInstructionsMd() { return instructionsMd; }
  public void setInstructionsMd(String instructionsMd) { this.instructionsMd = instructionsMd; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
  public OffsetDateTime getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
  public List<RecipeIngredient> getIngredients() { return ingredients; }
  public void setIngredients(List<RecipeIngredient> ingredients) { this.ingredients = ingredients; }
}

