package com.chefsupport.api.repository;

import com.chefsupport.api.domain.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
  Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

