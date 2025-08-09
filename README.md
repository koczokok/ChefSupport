# ChefSupport

Local-first, single-user AI meal planning and shopping assistant.

- Web: React + Vite + Tailwind + shadcn/ui
- API: Spring Boot + Postgres
- AI: FastAPI + LangGraph + Qdrant + Ollama (OpenAI fallback optional)

See .agent-os/product/ for product docs.

## Running locally

API:
- Requirements: Java 21, Maven
- Start: `cd apps/api && mvn spring-boot:run`
- Health: `GET http://localhost:8080/api/health`
- Endpoints:
  - `GET /api/recipes?q=&kcalMin=&kcalMax=&tags=&page=&size=` → `{ items, page, size, total }`
  - `POST /api/recipes` (RecipeCreate) → RecipeResponse
  - `GET /api/recipes/{id}` → RecipeResponse
  - `PUT /api/recipes/{id}` (RecipeUpdate) → RecipeResponse
  - `DELETE /api/recipes/{id}`

Web:
- Start: `cd apps/web && npm install && npm run dev`
- Open: `http://localhost:5173`

## Validation
- Server-side: Bean Validation (name required, servings >= 1; ingredient quantity >= 0)
- Client-side: React Hook Form + Zod
