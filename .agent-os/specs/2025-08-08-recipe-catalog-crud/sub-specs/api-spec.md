# API Specification

This is the API specification for the spec detailed in @.agent-os/specs/2025-08-08-recipe-catalog-crud/spec.md

## Endpoints

### GET /api/recipes
**Purpose:** List recipes with pagination and filters
**Parameters:** query (q), tags (csv), kcalMin, kcalMax, page, size
**Response:** { items: RecipeSummary[], page, size, total }
**Errors:** 400 invalid params

### POST /api/recipes
**Purpose:** Create a recipe
**Parameters:** JSON body RecipeCreate (name, servings, tags[], ingredients[], times, instructions)
**Response:** 201 with Recipe
**Errors:** 400 validation error

### GET /api/recipes/{id}
**Purpose:** Get a recipe by id
**Parameters:** id (uuid)
**Response:** Recipe
**Errors:** 404 not found

### PUT /api/recipes/{id}
**Purpose:** Update a recipe
**Parameters:** id (uuid), JSON body RecipeUpdate
**Response:** 200 with Recipe
**Errors:** 400 validation error; 404 not found

### DELETE /api/recipes/{id}
**Purpose:** Delete a recipe
**Parameters:** id (uuid)
**Response:** 204 no content
**Errors:** 404 not found
