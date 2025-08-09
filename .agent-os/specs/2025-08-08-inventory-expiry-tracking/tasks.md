# Spec Tasks

## Tasks

- [ ] 1. Back-end: DB schema & migrations
  - [ ] 1.1 Create Flyway `V2__inventory_tables.sql` for `product` and `inventory_lot`
  - [ ] 1.2 JPA entities/repositories for Product and InventoryLot
  - [ ] 1.3 Indexes on product.name and inventory_lot.expiry_date

- [ ] 2. Back-end: REST API & scheduling
  - [ ] 2.1 Controller/service DTOs and validators
  - [ ] 2.2 Products CRUD endpoints + tests
  - [ ] 2.3 Lots CRUD endpoints + filters (expiringInDays, productId) + tests
  - [ ] 2.4 Daily scheduler to compute expiring lots; expose via endpoint

- [ ] 3. Front-end: Inventory screens
  - [ ] 3.1 Product list + editor (name, unit, category)
  - [ ] 3.2 Lots list + editor (quantity, unit, expiry_date, location)
  - [ ] 3.3 Expiring soon view with days filter

- [ ] 4. Integration
  - [ ] 4.1 Planner hook: endpoint to fetch expiring lots by window
  - [ ] 4.2 Shopping list hook: suggest replenishment for soon-to-expire consumed items

- [ ] 5. Tests & docs
  - [ ] 5.1 API integration tests
  - [ ] 5.2 Front-end smoke tests
  - [ ] 5.3 Update README with endpoints
