package com.chefsupport.api.web;

import com.chefsupport.api.service.RecipeService;
import com.chefsupport.api.web.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
  private final RecipeService service;
  public RecipeController(RecipeService service) { this.service = service; }

  @GetMapping
  public RecipeListResponse list(
      @RequestParam(name = "q", required = false) String q,
      @RequestParam(name = "kcalMin", required = false) java.math.BigDecimal kcalMin,
      @RequestParam(name = "kcalMax", required = false) java.math.BigDecimal kcalMax,
      @RequestParam(name = "tags", required = false) List<String> tags,
      @RequestParam(name = "page", required = false, defaultValue = "0") int page,
      @RequestParam(name = "size", required = false, defaultValue = "20") int size
  ) {
    var p = service.list(q, kcalMin, kcalMax, tags, page, size);
    return new RecipeListResponse(p.getContent(), p.getNumber(), p.getSize(), p.getTotalElements());
  }

  @PostMapping
  public ResponseEntity<RecipeResponse> create(@RequestBody @Validated RecipeCreate body) {
    return ResponseEntity.status(201).body(service.create(body));
  }

  @GetMapping("/{id}")
  public RecipeResponse get(@PathVariable UUID id) { return service.get(id); }

  @PutMapping("/{id}")
  public RecipeResponse update(@PathVariable UUID id, @RequestBody @Validated RecipeUpdate body) { return service.update(id, body); }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) { service.delete(id); return ResponseEntity.noContent().build(); }
}

