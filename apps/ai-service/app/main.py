from fastapi import FastAPI

app = FastAPI(title="ChefSupport AI Service")

@app.get("/health")
async def health() -> dict:
    return {"status": "ok"}
