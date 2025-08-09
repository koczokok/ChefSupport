FROM python:3.11-slim
WORKDIR /app
COPY pyproject.toml ./
RUN pip install --no-cache-dir uv && uv pip install -r <(uv pip compile --generate-hashes --quiet) 2>/dev/null || pip install --no-cache-dir fastapi uvicorn pydantic httpx
COPY app ./app
EXPOSE 8000
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000"]
