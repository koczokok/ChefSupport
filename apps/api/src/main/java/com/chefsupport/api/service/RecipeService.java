package com.chefsupport.api.service;

import com.chefsupport.api.domain.Recipe;
import com.chefsupport.api.domain.RecipeIngredient;
import com.chefsupport.api.repository.RecipeRepository;
import com.chefsupport.api.web.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@Transactional
public class RecipeService {
  private final RecipeRepository repository;
  private final NutritionCalculator calculator;

  public RecipeService(RecipeRepository repository, NutritionCalculator calculator) {
    this.repository = repository;
    this.calculator = calculator;
  }

  public RecipeResponse create(RecipeCreate req) {
    Recipe recipe = toEntity(req);
    BigDecimal kcal = calculator.estimateKcalPerServing(recipe);
    recipe.setKcalPerServing(kcal);
    Recipe saved = repository.save(recipe);
    return toResponse(saved);
  }

  public RecipeResponse update(UUID id, RecipeUpdate req) {
    Recipe recipe = repository.findById(id).orElseThrow();
    applyUpdate(recipe, req);
    BigDecimal kcal = calculator.estimateKcalPerServing(recipe);
    recipe.setKcalPerServing(kcal);
    return toResponse(recipe);
  }

  public void delete(UUID id) { repository.deleteById(id); }

  @Transactional(readOnly = true)
  public RecipeResponse get(UUID id) { return repository.findById(id).map(this::toResponse).orElseThrow(); }

  @Transactional(readOnly = true)
  public Page<RecipeSummary> list(String q, BigDecimal kcalMin, BigDecimal kcalMax, List<String> tags, int page, int size) {
    Pageable pageable = PageRequest.of(Math.max(page,0), Math.max(size,1));
    List<Recipe> filtered = repository.findAll().stream()
        .filter(r -> q == null || q.isBlank() || r.getName().toLowerCase().contains(q.toLowerCase()))
        .filter(r -> kcalMin == null || (r.getKcalPerServing() != null && r.getKcalPerServing().compareTo(kcalMin) >= 0))
        .filter(r -> kcalMax == null || (r.getKcalPerServing() != null && r.getKcalPerServing().compareTo(kcalMax) <= 0))
        .filter(r -> tags == null || tags.isEmpty() || (r.getTags() != null && r.getTags().containsAll(tags)))
        .toList();
    int from = Math.min(pageable.getPageNumber() * pageable.getPageSize(), filtered.size());
    int to = Math.min(from + pageable.getPageSize(), filtered.size());
    List<RecipeSummary> content = filtered.subList(from, to).stream()
        .map(r -> new RecipeSummary(r.getId(), r.getName(), r.getServings(), r.getKcalPerServing(), r.getTags()))
        .toList();
    return new PageImpl<>(content, pageable, filtered.size());
  }

  private Recipe toEntity(RecipeCreate req) {
    Recipe r = new Recipe();
    r.setName(req.getName());
    r.setServings(req.getServings());
    r.setPrepMinutes(req.getPrepMinutes());
    r.setCookMinutes(req.getCookMinutes());
    r.setTags(new ArrayList<>(req.getTags() == null ? List.of() : req.getTags()));
    r.setInstructionsMd(req.getInstructionsMd());
    List<RecipeIngredient> ings = new ArrayList<>();
    if (req.getIngredients() != null) {
      for (IngredientDto d : req.getIngredients()) {
        RecipeIngredient ing = new RecipeIngredient();
        ing.setRecipe(r);
        ing.setName(d.getName());
        ing.setQuantity(d.getQuantity());
        ing.setUnit(d.getUnit());
        ing.setNotes(d.getNotes());
        ings.add(ing);
      }
    }
    r.setIngredients(ings);
    return r;
  }

  private void applyUpdate(Recipe r, RecipeUpdate req) {
    r.setName(req.getName());
    r.setServings(req.getServings());
    r.setPrepMinutes(req.getPrepMinutes());
    r.setCookMinutes(req.getCookMinutes());
    r.setTags(new ArrayList<>(req.getTags() == null ? List.of() : req.getTags()));
    r.setInstructionsMd(req.getInstructionsMd());
    r.getIngredients().clear();
    if (req.getIngredients() != null) {
      for (IngredientDto d : req.getIngredients()) {
        RecipeIngredient ing = new RecipeIngredient();
        ing.setRecipe(r);
        ing.setName(d.getName());
        ing.setQuantity(d.getQuantity());
        ing.setUnit(d.getUnit());
        ing.setNotes(d.getNotes());
        r.getIngredients().add(ing);
      }
    }
  }

  private RecipeResponse toResponse(Recipe r) {
    List<IngredientDto> ings = r.getIngredients().stream()
        .map(i -> new IngredientDto(i.getId(), i.getName(), i.getQuantity(), i.getUnit(), i.getNotes()))
        .toList();
    return new RecipeResponse(r.getId(), r.getName(), r.getServings(), r.getPrepMinutes(), r.getCookMinutes(), r.getTags(), r.getKcalPerServing(), ings, r.getInstructionsMd());
  }
}

