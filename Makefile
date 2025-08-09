SHELL := bash

# Infra
infra-up:
	cd infra && docker compose up -d --build

infra-down:
	cd infra && docker compose down -v

# API
api-run:
	cd apps/api && mvn spring-boot:run

api-build:
	cd apps/api && mvn -q -DskipTests package

# AI Service
ai-run:
	cd apps/ai-service && uvicorn app.main:app --reload --host 0.0.0.0 --port 8000

# Web
web-install:
	cd apps/web && npm install

web-dev:
	cd apps/web && npm run dev

web-build:
	cd apps/web && npm run build

web-preview:
	cd apps/web && npm run preview

