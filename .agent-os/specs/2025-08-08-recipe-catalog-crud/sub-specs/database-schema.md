# Database Schema

This is the database schema implementation for the spec detailed in @.agent-os/specs/2025-08-08-recipe-catalog-crud/spec.md

## Changes

- Table `recipe`
  - id (uuid, pk)
  - name (text, not null)
  - servings (int, not null)
  - prep_minutes (int)
  - cook_minutes (int)
  - tags (text[])
  - kcal_per_serving (numeric(10,2))
  - instructions_md (text)
  - created_at (timestamptz)
  - updated_at (timestamptz)

- Table `recipe_ingredient`
  - id (uuid, pk)
  - recipe_id (uuid, fk->recipe)
  - product_id (uuid, null) -- optional link to product catalog later
  - name (text, not null)
  - quantity (numeric(12,3))
  - unit (text)
  - notes (text)

## Specifications

- Indexes:
  - GIN on recipe.tags
  - btree on recipe.name (trigram optional later)
- Foreign keys:
  - recipe_ingredient.recipe_id -> recipe.id ON DELETE CASCADE
- Migrations:
  - Use Flyway, create V1__create_recipe_tables.sql

## Rationale

- Denormalizing kcal_per_serving speeds list filtering and avoids re-computation on every read.
- Array tags allow quick filtering for single-user MVP; can normalize later if needed.
