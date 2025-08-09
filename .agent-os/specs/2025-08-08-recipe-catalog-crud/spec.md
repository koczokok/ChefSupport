# Spec Requirements Document

> Spec: Recipe catalog CRUD with tags and nutrition estimates
> Created: 2025-08-08

## Overview

Provide a recipe catalog with create/read/update/delete functionality, tagging, and automatic nutrition estimates per recipe/serving to support the 1.5-week planner and shopping list generation.

## User Stories

### Manage recipes
As a single user, I want to create, view, edit, and delete recipes with servings, instructions, and tags so that I can maintain a personal catalog.

Detailed workflow: From the Recipes screen, I can add a recipe with name, servings, prep/cook time, tags, ingredients (quantity + unit + free text), and instructions. I can edit fields and see validations.

### See nutrition estimates
As a user, I want the app to estimate calories per serving so that I can plan against daily calorie targets.

Detailed workflow: On save (or preview), the app computes a rough kcal/serving estimate from ingredient quantities and known product nutrition where available; otherwise uses heuristic averages.

### Search and filter
As a user, I want to search and filter recipes by tag, name, and calorie range so that I can quickly find suitable options.

Detailed workflow: Recipes list supports text search, tag chips, and kcal range filter; results paginate.

## Spec Scope

1. **Recipe CRUD** - Create, read, update, delete recipes with servings, times, tags, instructions.
2. **Ingredients model** - Line items per recipe: name/product reference (optional), quantity, unit, notes.
3. **Tags & filtering** - Store tags (text[]) and enable filtering by name, tags, kcal range.
4. **Nutrition estimate** - Compute kcal/serving and store denormalized value per recipe.
5. **API & UI** - REST endpoints and React screens with validations.

## Out of Scope

- URL-based recipe import (AI parsing)
- Substitutions and AI suggestions
- Vector embeddings and semantic search
- Multi-user or sharing

## Expected Deliverable

1. Browser-visible Recipes list and editor with working CRUD and filters.
2. API and DB schema changes deployed; kcal/serving displayed and stored.

