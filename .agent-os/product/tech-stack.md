# Technical Stack

## Core Application
- application_framework: Java Spring Boot 3.3 (REST + WebSocket)
- database_system: PostgreSQL 16 (primary relational store)
- javascript_framework: React 18 + Vite (PWA)
- import_strategy: node
- css_framework: Tailwind CSS 3
- ui_component_library: shadcn/ui (Radix UI + Tailwind)
- fonts_provider: Google Fonts
- icon_library: Lucide
- application_hosting: Local Docker Compose (single-user), optional small VPS
- database_hosting: Local Docker (Postgres)
- asset_hosting: Local filesystem volume (Docker)
- deployment_solution: Docker Compose (multi-service), optional Fly.io/Render
- code_repository_url: git@github.com:koczokok/ChefSupport.git

## AI & Agents
- ai_service: Python 3.11 + FastAPI
- agent_orchestration: LangGraph (LangChain core) with tool-calling
- model_runtime (local): Ollama (e.g., Llama 3.1 8B/13B) with GPU-optional
- model_runtime (remote optional): OpenAI API (fallback, toggleable)
- vector_database: Qdrant (Docker) for embeddings of recipes, pantry items, notes
- embeddings: nomic-embed-text (local via Ollama) or OpenAI text-embedding-3-small (remote)

## Integrations & Utilities
- scheduler: Quartz (Spring) for daily checks; APScheduler (Python AI svc) if needed
- background jobs: Spring @Scheduled + durable retries
- auth: Local-only single-user (no cloud auth); optional local password/Passkey
- observability: OpenTelemetry SDKs → console/logs; optional Grafana/Loki/Tempo via Docker

## Preferences & Defaults
- units: metric
- calorie_target: user-defined (configurable in Settings)
- nutrition_data: USDA FoodData Central (local import supported)

## Rationale
- Spring Boot provides robust, type-safe APIs, WebSockets, scheduling, and persistence.
- Python AI microservice isolates ML dependencies, enabling LangGraph/LLM evolution without impacting the core app.
- Local-first via Docker Compose matches single-user, budget-free privacy goals.

