# Technical Specification

This is the technical specification for the spec detailed in @.agent-os/specs/2025-08-08-recipe-catalog-crud/spec.md

## Technical Requirements

- Domain model: `recipe`, `recipe_ingredient`, `tag` (or string[]), kcal/serving denormalized on `recipe`
- API endpoints:
  - GET /api/recipes?query=&tags=&kcalMin=&kcalMax=&page=&size=
  - POST /api/recipes
  - GET /api/recipes/{id}
  - PUT /api/recipes/{id}
  - DELETE /api/recipes/{id}
- Validation: name required, servings > 0, ingredient quantity >= 0
- Search: ILIKE on name; tag filter; kcal range
- Nutrition: server computes kcal_per_serving on create/update from ingredient data
- UI:
  - Recipes list with search, tag chips, kcal range inputs
  - Recipe editor form (React Hook Form + Zod later), basic HTML form for MVP
- Performance: p50 < 150ms for list and detail; pagination size 20

## External Dependencies (Conditional)

- None new for API (Spring stack already chosen)
- Optional: simple kcal heuristics module (local util)
