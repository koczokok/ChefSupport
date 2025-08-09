# Spec Tasks

## Tasks

- [ ] 1. Back-end: Database schema and domain model
  - [ ] 1.1 Write tests for nutrition calculator service (kcal per serving from ingredients)
  - [ ] 1.2 Create Flyway migration `V1__create_recipe_tables.sql` for `recipe` and `recipe_ingredient`
  - [ ] 1.3 Implement JPA entities and repositories (`Recipe`, `RecipeIngredient`)
  - [ ] 1.4 Implement nutrition calculator service and unit tests
  - [ ] 1.5 Verify app boots with Postgres and schema applies

- [ ] 2. Back-end: REST API endpoints
  - [ ] 2.1 Write controller tests (MockMvc) for CRUD and filters
  - [ ] 2.2 Implement DTOs and mappers (entity ⇄ API models)
  - [ ] 2.3 Implement controllers and services for:
        - GET /api/recipes (search, tags, kcal range, pagination)
        - POST /api/recipes
        - GET /api/recipes/{id}
        - PUT /api/recipes/{id}
        - DELETE /api/recipes/{id}
  - [ ] 2.4 Add validation (name required, servings > 0, non-negative quantities)
  - [ ] 2.5 Ensure kcal_per_serving computed on create/update and persisted
  - [ ] 2.6 Verify all controller tests pass

- [ ] 3. Front-end: Recipes list and filters (MVP)
  - [ ] 3.1 Write component test stubs for list filtering
  - [ ] 3.2 Implement Recipes list page with search, tag chips, kcal min/max inputs, pagination
  - [ ] 3.3 Wire to API (fetch list with query params); render loading/empty states
  - [ ] 3.4 Manual smoke test in browser

- [ ] 4. Front-end: Recipe editor (MVP)
  - [ ] 4.1 Write component test stubs for form submit and validation
  - [ ] 4.2 Implement Recipe editor form (name, servings, times, tags, ingredients list, instructions)
  - [ ] 4.3 POST/PUT to API; show kcal/serving from API response
  - [ ] 4.4 Manual smoke test: create/edit/delete recipe end-to-end

- [ ] 5. Docs & cleanup
  - [ ] 5.1 Update README with local run instructions for API endpoints
  - [ ] 5.2 Record any follow-ups in roadmap (e.g., advanced nutrition, RHF/Zod adoption)
  - [ ] 5.3 Verify all tests pass
