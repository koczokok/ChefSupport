# Database Schema

This is the database schema implementation for the spec detailed in @.agent-os/specs/2025-08-08-inventory-expiry-tracking/spec.md

## Changes

- Table `product`
  - id (uuid pk)
  - name (text not null)
  - unit (text)
  - category (text)
  - created_at (timestamptz default now())
  - updated_at (timestamptz default now())

- Table `inventory_lot`
  - id (uuid pk)
  - product_id (uuid fk->product)
  - quantity (numeric(12,3))
  - unit (text)
  - expiry_date (date)
  - location (text)
  - notes (text)
  - created_at (timestamptz default now())

## Specifications

- Indexes:
  - btree on `product(name)`
  - btree on `inventory_lot(expiry_date)`
- Foreign keys:
  - `inventory_lot.product_id` -> `product.id` ON DELETE CASCADE
- Migrations:
  - `V2__inventory_tables.sql`

## Rationale

- Lots enable multiple expiry dates per product; expiry_date index supports expiring queries
