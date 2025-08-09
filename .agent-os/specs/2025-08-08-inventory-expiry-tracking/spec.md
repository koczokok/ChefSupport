# Spec Requirements Document

> Spec: Inventory & Expiry Tracking (CRUD, notifications)
> Created: 2025-08-08

## Overview

Provide local-first inventory management of products and lots with quantities and expiry dates, and notify the user of items nearing expiry so the planner can prioritize usage and shopping can be minimized.

## User Stories

### Track products and quantities
As a user, I want to record what products I have, their quantities, and units so that the system knows what ingredients are available for recipes.

Detailed workflow: From Inventory, add a product (name, unit, category/aisle) and one or more lots with quantity and expiry date. Update or delete lots as items are consumed or discarded.

### See upcoming expiries
As a user, I want to see items expiring soon so that I can use them first or buy replacements.

Detailed workflow: Inventory dashboard shows "Expiring in X days" list; system sends a daily notification and marks items with urgency badges.

### Auto-recommend consumption/replenishment windows
As a user, I want suggestions on when to use or replenish items so that I avoid waste and avoid extra shopping trips.

Detailed workflow: For each item reaching a threshold (days-to-expiry), suggest a recipe fit or add to next shopping list.

## Spec Scope

1. **Product catalog CRUD** - Product(name, unit, category/aisle)
2. **Inventory lots CRUD** - Lots(product_id, quantity, unit, expiry_date, location/notes)
3. **Expiry views & filters** - List expiring in N days, sort by urgency
4. **Notifications** - Daily check with expiring items summary
5. **Integration points** - Hooks for planner and shopping list generation

## Out of Scope

- Price tracking and cost optimization
- Multi-user inventory sharing
- Barcode scanning and OCR

## Expected Deliverable

1. Browser-visible Inventory screens to manage products and lots, with expiring view and filters
2. API endpoints and DB schema in place; daily scheduler generates an expiring items list/notification
