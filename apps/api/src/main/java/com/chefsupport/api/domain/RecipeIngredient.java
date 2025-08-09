package com.chefsupport.api.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipe_id", nullable = false)
  private Recipe recipe;

  private UUID productId;

  @Column(nullable = false)
  private String name;

  private BigDecimal quantity;

  private String unit;

  private String notes;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public Recipe getRecipe() { return recipe; }
  public void setRecipe(Recipe recipe) { this.recipe = recipe; }
  public UUID getProductId() { return productId; }
  public void setProductId(UUID productId) { this.productId = productId; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public BigDecimal getQuantity() { return quantity; }
  public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
  public String getUnit() { return unit; }
  public void setUnit(String unit) { this.unit = unit; }
  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }
}

