# API Specification

This is the API specification for the spec detailed in @.agent-os/specs/2025-08-08-inventory-expiry-tracking/spec.md 

## Endpoints

### Products
- GET /api/products?q=&page=&size=
- POST /api/products
- GET /api/products/{id}
- PUT /api/products/{id}
- DELETE /api/products/{id}

### Inventory Lots
- GET /api/inventory/lots?productId=&expiringInDays=&page=&size=
- POST /api/inventory/lots
- GET /api/inventory/lots/{id}
- PUT /api/inventory/lots/{id}
- DELETE /api/inventory/lots/{id}

### Notifications
- GET /api/inventory/expiring?days=

