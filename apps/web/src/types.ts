export type RecipeSummary = {
  id: string
  name: string
  servings: number
  kcalPerServing?: number
  tags?: string[]
}

export type RecipeListResponse = {
  items: RecipeSummary[]
  page: number
  size: number
  total: number
}

export type IngredientDto = {
  id?: string
  name: string
  quantity?: number
  unit?: string
  notes?: string
}

export type RecipeCreate = {
  name: string
  servings: number
  prepMinutes?: number
  cookMinutes?: number
  tags?: string[]
  ingredients?: IngredientDto[]
  instructionsMd?: string
}

export type RecipeUpdate = RecipeCreate

export type RecipeResponse = Required<Omit<RecipeCreate, 'tags' | 'ingredients'>> & {
  id: string
  tags: string[]
  ingredients: Required<IngredientDto>[]
  kcalPerServing?: number
}

