package com.chefsupport.api.service;

import com.chefsupport.api.domain.Recipe;
import com.chefsupport.api.domain.RecipeIngredient;
import com.chefsupport.api.repository.RecipeRepository;
import com.chefsupport.api.web.dto.IngredientDto;
import com.chefsupport.api.web.dto.RecipeCreate;
import com.chefsupport.api.web.dto.RecipeResponse;
import com.chefsupport.api.web.dto.RecipeUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

  RecipeRepository repository;
  NutritionCalculator calculator;
  RecipeService service;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(RecipeRepository.class);
    calculator = Mockito.mock(NutritionCalculator.class);
    service = new RecipeService(repository, calculator);
  }

  @Test
  void create_calculatesKcal_andSaves() {
    RecipeCreate req = new RecipeCreate();
    req.setName("Pasta");
    req.setServings(2);
    req.setIngredients(List.of(new IngredientDto(null, "rice", BigDecimal.valueOf(200), "g", null)));

    when(calculator.estimateKcalPerServing(any(Recipe.class))).thenReturn(new BigDecimal("650.00"));
    when(repository.save(any(Recipe.class))).thenAnswer(inv -> inv.getArgument(0));

    RecipeResponse resp = service.create(req);

    assertEquals("Pasta", resp.getName());
    assertEquals(new BigDecimal("650.00"), resp.getKcalPerServing());

    ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
    verify(repository).save(captor.capture());
    Recipe saved = captor.getValue();
    assertEquals("Pasta", saved.getName());
    assertEquals(1, saved.getIngredients().size());
  }

  @Test
  void update_recomputes_kcal() {
    UUID id = UUID.randomUUID();
    Recipe existing = new Recipe();
    existing.setServings(2);
    existing.setName("Old");
    existing.setIngredients(List.of(new RecipeIngredient()));
    when(repository.findById(id)).thenReturn(Optional.of(existing));
    when(calculator.estimateKcalPerServing(any(Recipe.class))).thenReturn(new BigDecimal("500.00"));

    RecipeUpdate req = new RecipeUpdate();
    req.setName("New");
    req.setServings(3);
    req.setIngredients(List.of(new IngredientDto(null, "chicken", BigDecimal.valueOf(300), "g", null)));

    RecipeResponse resp = service.update(id, req);

    assertEquals("New", resp.getName());
    assertEquals(3, resp.getServings());
    assertEquals(new BigDecimal("500.00"), resp.getKcalPerServing());
  }

  @Test
  void list_applies_basic_filters() {
    Recipe a = new Recipe();
    a.setName("Pasta");
    a.setServings(2);
    a.setKcalPerServing(new BigDecimal("600"));

    Recipe b = new Recipe();
    b.setName("Salad");
    b.setServings(1);
    b.setKcalPerServing(new BigDecimal("200"));

    when(repository.findAll()).thenReturn(List.of(a, b));

    var results = service.list("pa", new BigDecimal("500"), null, List.of());
    assertEquals(1, results.size());
    assertEquals("Pasta", results.get(0).getName());
  }
}

