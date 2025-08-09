CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS recipe (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name TEXT NOT NULL,
  servings INTEGER NOT NULL,
  prep_minutes INTEGER,
  cook_minutes INTEGER,
  kcal_per_serving NUMERIC(10,2),
  instructions_md TEXT,
  created_at TIMESTAMPTZ DEFAULT now(),
  updated_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE IF NOT EXISTS recipe_ingredient (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  recipe_id UUID NOT NULL REFERENCES recipe(id) ON DELETE CASCADE,
  product_id UUID,
  name TEXT NOT NULL,
  quantity NUMERIC(12,3),
  unit TEXT,
  notes TEXT
);

CREATE INDEX IF NOT EXISTS idx_recipe_name ON recipe (name);

-- Tags as a separate collection table to match JPA @ElementCollection
CREATE TABLE IF NOT EXISTS recipe_tags (
  recipe_id UUID NOT NULL REFERENCES recipe(id) ON DELETE CASCADE,
  tag TEXT NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_recipe_tags_recipe ON recipe_tags(recipe_id);
CREATE INDEX IF NOT EXISTS idx_recipe_tags_tag ON recipe_tags(tag);
