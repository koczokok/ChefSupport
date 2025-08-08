# Product Roadmap

## Phase 1: MVP Planner

**Goal:** Local-first single-user MVP with meal planning, shopping list, and inventory basics
**Success Criteria:** Plan 10–11 days; generate consolidated shopping list; track expiries; no fatal bugs

### Features
- [ ] Recipe catalog CRUD with tags and nutrition estimates `M`
- [ ] Inventory & expiry tracking (CRUD, notifications) `M`
- [ ] 1.5-week planner with calorie targets (basic heuristic) `L`
- [ ] Shopping list generation, de-duplication, category grouping `M`
- [ ] PWA shell with offline cache and basic sync `S`
- [ ] Local Docker Compose: Spring Boot, Postgres, Qdrant, AI svc `S`

### Dependencies
- Postgres, Qdrant, Ollama (optional), Docker Compose

## Phase 2: Agentic Planning & Replan

**Goal:** Agent orchestrator that accounts for inventory, expiry, calories; dynamic replan
**Success Criteria:** Agent produces explainable plan; supports substitutions; replan after changes

### Features
- [ ] LangGraph-based planning agent with tool-calling `L`
- [ ] Embeddings pipeline (recipes + pantry) into Qdrant `M`
- [ ] What-can-I-cook suggestions from inventory `M`
- [ ] Substitution suggestions via model tools `M`
- [ ] Calendar export (ICS) and simple schedule view `S`

### Dependencies
- AI svc, embeddings, vector DB

## Phase 3: Polish & Scale

**Goal:** Robust UX, performance, and observability
**Success Criteria:** <200ms API p50 for core endpoints; stable offline behavior; clear logs/metrics

### Features
- [ ] Advanced nutrition (macros/micros) and weekly summaries `M`
- [ ] Batch cooking support and leftovers planning `M`
- [ ] Import recipes from URL with AI parsing `M`
- [ ] WebSocket live updates and background job monitoring `S`
- [ ] Backups/export/import of data `S`

### Dependencies
- Observability stack (optional), backup tooling

