# Technical Specification

This is the technical specification for the spec detailed in @.agent-os/specs/2025-08-08-inventory-expiry-tracking/spec.md

## Technical Requirements

- Domain model:
  - `product` (id, name, unit, category, created_at, updated_at)
  - `inventory_lot` (id, product_id, quantity, unit, expiry_date, location, notes, created_at)
- API endpoints:
  - Products: CRUD
  - Lots: CRUD, list expiring in `days` param
- Scheduling:
  - Daily @ 08:00: detect expiring lots within N days (default 3), persist summary log and expose via endpoint
- UI:
  - Inventory screen: products table and lots table
  - Expiring soon view with filters (days)
- Performance: p50 < 150ms typical queries; indexes on expiry_date, product name

## External Dependencies

- None beyond existing stack
