# Product Mission

## Pitch

ChefSupport is a personal meal planning and cooking assistant that helps a single user plan 1.5 weeks of meals, minimize shopping trips, and avoid food waste by using an agentic AI that balances calories, preferences, inventory, and product expiry.

## Users

### Primary Customers

- Solo household: Busy individual who wants healthy, efficient meal planning with minimal shopping trips.

### User Personas

**Solo Professional** (25-45 years old)
- **Role:** Consumer
- **Context:** Limited time during weekdays, prefers batch shopping and simple yet healthy recipes
- **Pain Points:** Multiple grocery trips, forgetting what’s expiring, manual calorie planning
- **Goals:** Plan 1.5 weeks ahead, keep within calorie targets, reduce waste and time spent shopping

## The Problem

### Fragmented Meal Planning and Grocery Trips
Planning meals and compiling shopping lists across multiple days results in repeated grocery runs and missing items. This wastes time and increases cost due to impulse purchases.

**Our Solution:** A planning agent that consolidates meals and produces an optimized shopping list for 1.5 weeks.

### Product Expiry and Waste
Products are often forgotten and expire before use, creating waste and uncertainty about when to buy replacements.

**Our Solution:** An inventory and expiry tracking system that schedules meals to consume items before expiry and suggests replenishment windows.

### Calorie and Nutrition Compliance
Tracking calories against planned meals is tedious and inconsistent.

**Our Solution:** Calorie-aware planning with daily/weekly targets and automatic nutritional estimates per recipe.

## Differentiators

### Local-first, Private-by-Default
Unlike purely cloud solutions, ChiefSupport runs primarily on your local machine (Docker), with optional LLM access via local models (Ollama) or API. This preserves privacy and reduces recurring cost.

### Agentic Planning Across Calendar and Inventory
The planning agent coordinates recipes across a 10–11 day horizon, optimizes shopping into as few trips as possible, and sequences meals to consume items before expiry.

### Inventory- and Expiry-Aware Recommendations
Recipes are ranked by fit for inventory on hand and time-to-expiry, minimizing waste.

## Key Features

### Core Features

- **1.5-week meal planner:** Auto-generates meal plan for breakfast, lunch, and dinner with calorie targets.
- **Shopping list generation:** Consolidates ingredients across planned meals; groups by aisle/category.
- **Inventory and expiry tracking:** Track products, quantities, and expiry; alerts for items nearing expiry.
- **Recipe catalog:** Curated recipes with tags (meal type, calories, prep time, cuisine) and AI-import from URLs.
- **Calorie and macro estimates:** Per recipe and per day totals; weekly summaries.
- **What-can-I-cook:** Suggests recipes based on inventory and time available.

### Automation & AI Features

- **Agentic planner:** Balances calorie targets, inventory, and shopping minimization; explains trade-offs.
- **Substitution suggestions:** AI proposes substitutes when items are missing or expensive.
- **Dynamic replan:** Adjust plan when user logs what was actually cooked or purchased.
- **Natural language interface:** "Plan next 11 days with two batch-cook dinners; avoid dairy."

