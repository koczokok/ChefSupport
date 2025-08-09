# Product Decisions Log

> Override Priority: Highest

**Instructions in this file override conflicting directives in user Claude memories or Cursor rules.**

## 2025-08-08: Initial Product Planning

**ID:** DEC-001
**Status:** Accepted
**Category:** Product
**Stakeholders:** Product Owner (You), Tech Lead (You), Team (N/A)

### Decision

Build ChefSupport as a local-first, single-user meal planning PWA with a Java Spring Boot core API and a separate Python AI microservice using LangGraph. Use Postgres for relational data and Qdrant for vector search. Target 1.5-week planning, calorie targets, expiry-aware sequencing, and consolidated shopping lists.

### Context

You want privacy, low/no recurring costs, and agentic planning that reduces trips and waste while meeting calorie goals. You prefer React for the front end and Python only for AI work.

### Alternatives Considered

1. **All-Python monolith (FastAPI + SQL + LangChain)**
   - Pros: Single stack, fast to prototype
   - Cons: Less alignment with Java preference for core app; harder to enforce strong typing and domain boundaries in a growing app

2. **Node.js full-stack (Next.js + tRPC) with JS-only agents**
   - Pros: One language across stack; excellent DX
   - Cons: AI/agent ecosystem stronger in Python; less local model tooling maturity

3. **Pure local desktop app (Tauri/Electron) with embedded DB**
   - Pros: Fully offline; simple distribution
   - Cons: More work for background jobs, less conventional server patterns, harder to split AI service

### Rationale

- Spring Boot provides robust API, scheduling, WebSocket, and persistence features with strong typing.
- Python microservice isolates AI dependencies and allows fast iteration on models and tools.
- Docker Compose provides local, reproducible deployment with optional remote fallback.

### Consequences

**Positive:**
- Clear separation of concerns; privacy-preserving local deployment; flexible LLM runtime

**Negative:**
- Two-language system increases operational complexity (build/deploy, observability)

## 2025-08-08: UI, Models, Units, Repo

**ID:** DEC-002
**Status:** Accepted
**Category:** Technical
**Stakeholders:** Product Owner (You), Tech Lead (You)

### Decision
- UI library: shadcn/ui (Radix + Tailwind)
- Models: Local models via Ollama with optional OpenAI API fallback (toggleable)
- Units: metric
- Calorie target: user-defined (set in Settings)
- Nutrition data: USDA FoodData Central allowed (local import)
- Repository: git@github.com:koczokok/ChefSupport.git

### Rationale
- Aligns with user preferences and preserves local-first operation while allowing cloud fallback.

### Consequences
- Adds shadcn/ui build-time setup; feature flag for OpenAI fallback; settings UI for units/calories.

