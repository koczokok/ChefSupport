package com.chefsupport.api.web.dto;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class IngredientDto {
  private UUID id;
  @NotBlank
  private String name;
  @PositiveOrZero
  private BigDecimal quantity;
  private String unit;
  private String notes;

  public IngredientDto() {}

  public IngredientDto(UUID id, String name, BigDecimal quantity, String unit, String notes) {
    this.id = id; this.name = name; this.quantity = quantity; this.unit = unit; this.notes = notes;
  }

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public BigDecimal getQuantity() { return quantity; }
  public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
  public String getUnit() { return unit; }
  public void setUnit(String unit) { this.unit = unit; }
  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }
}

