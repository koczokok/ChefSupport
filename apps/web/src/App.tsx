import { useEffect, useMemo, useState } from 'react'
import type { IngredientDto, RecipeCreate, RecipeResponse, RecipeSummary, RecipeListResponse } from './types'
import { useForm, useFieldArray } from 'react-hook-form'
import { z } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'

export default function App() {
  const [q, setQ] = useState('')
  const [kcalMin, setKcalMin] = useState<string>('')
  const [kcalMax, setKcalMax] = useState<string>('')
  const [recipes, setRecipes] = useState<RecipeSummary[]>([])
  const [page, setPage] = useState(0)
  const [size, setSize] = useState(20)
  const [total, setTotal] = useState(0)
  const [loading, setLoading] = useState(false)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState<RecipeCreate>({ name: '', servings: 1, ingredients: [] })
  const [editId, setEditId] = useState<string | null>(null)
  const [tagFilter, setTagFilter] = useState('')

  const query = useMemo(() => {
    const params = new URLSearchParams()
    if (q) params.set('q', q)
    if (kcalMin) params.set('kcalMin', kcalMin)
    if (kcalMax) params.set('kcalMax', kcalMax)
    if (tagFilter) params.set('tags', tagFilter.split(',').map(s=>s.trim()).filter(Boolean).join(','))
    params.set('page', String(page))
    params.set('size', String(size))
    return params.toString()
  }, [q, kcalMin, kcalMax, tagFilter, page, size])

  useEffect(() => {
    const controller = new AbortController()
    const fetchData = async () => {
      setLoading(true)
      try {
        const url = `http://localhost:8080/api/recipes${query ? `?${query}` : ''}`
        const res = await fetch(url, { signal: controller.signal })
        const data: RecipeListResponse = await res.json()
        setRecipes(data.items)
        setPage(data.page)
        setSize(data.size)
        setTotal(data.total)
      } catch (e) {
        if (!(e instanceof DOMException && e.name === 'AbortError')) console.error(e)
      } finally {
        setLoading(false)
      }
    }
    fetchData()
    return () => controller.abort()
  }, [query])

  const refreshList = async () => {
    const res = await fetch('http://localhost:8080/api/recipes')
    setRecipes(await res.json())
  }

  return (
    <div className="p-6 space-y-6">
      <header>
        <h1 className="text-2xl font-bold">ChefSupport</h1>
        <p className="text-gray-600">Recipes</p>
      </header>

      <section className="flex flex-wrap gap-3 items-end">
        <div>
          <label className="block text-sm text-gray-700">Search</label>
          <input value={q} onChange={e => setQ(e.target.value)} className="border rounded px-2 py-1" placeholder="name..." />
        </div>
        <div>
          <label className="block text-sm text-gray-700">kcal min</label>
          <input value={kcalMin} inputMode="numeric" pattern="[0-9]*" onChange={e => setKcalMin(e.target.value.replace(/[^0-9]/g, ''))} className="border rounded px-2 py-1 w-24" />
        </div>
        <div>
          <label className="block text-sm text-gray-700">kcal max</label>
          <input value={kcalMax} inputMode="numeric" pattern="[0-9]*" onChange={e => setKcalMax(e.target.value.replace(/[^0-9]/g, ''))} className="border rounded px-2 py-1 w-24" />
        </div>
        <div>
          <label className="block text-sm text-gray-700">Tags (comma separated)</label>
          <input value={tagFilter} onChange={e => setTagFilter(e.target.value)} className="border rounded px-2 py-1" placeholder="e.g. dinner, quick" />
        </div>
      </section>

      <section className="space-y-4">
        <div>
          <button className="border rounded px-3 py-1 bg-sky-600 text-white" onClick={() => setShowForm(s => !s)}>
            {showForm ? 'Close' : 'New Recipe'}
          </button>
        </div>
        {showForm && (
          <RecipeForm
            value={form}
            mode={editId ? 'edit' : 'create'}
            onChange={setForm}
            onCancel={() => { setShowForm(false); setEditId(null); setForm({ name: '', servings: 1, ingredients: [] }) }}
            onSubmit={async () => {
              if (editId) {
                await fetch(`http://localhost:8080/api/recipes/${editId}`, { method: 'PUT', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(form) })
              } else {
                await fetch('http://localhost:8080/api/recipes', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(form) })
              }
              setShowForm(false)
              setEditId(null)
              setForm({ name: '', servings: 1, ingredients: [] })
              await refreshList()
            }}
          />
        )}
        {loading ? (
          <p>Loading...</p>
        ) : recipes.length === 0 ? (
          <p className="text-gray-500">No recipes found</p>
        ) : (
          <>
            <ul className="divide-y">
              {recipes.map(r => (
                <li key={r.id} className="py-3 flex items-center justify-between">
                  <div>
                    <div className="font-medium">{r.name}</div>
                    <div className="text-sm text-gray-600">Servings: {r.servings} • kcal/serv: {r.kcalPerServing ?? '—'}</div>
                    {r.tags && r.tags.length > 0 && (
                      <div className="mt-1 flex gap-1 flex-wrap">
                        {r.tags.map(t => (
                          <span key={t} className="text-xs bg-gray-100 border rounded px-1">{t}</span>
                        ))}
                      </div>
                    )}
                  </div>
                  <div className="flex gap-2">
                    <button className="text-sky-700" onClick={async () => {
                      const res = await fetch(`http://localhost:8080/api/recipes/${r.id}`)
                      const data: RecipeResponse = await res.json()
                      setForm({
                        name: data.name,
                        servings: data.servings,
                        prepMinutes: data.prepMinutes,
                        cookMinutes: data.cookMinutes,
                        tags: data.tags,
                        ingredients: data.ingredients.map(i => ({ name: i.name, quantity: i.quantity, unit: i.unit, notes: i.notes })),
                        instructionsMd: data.instructionsMd,
                      })
                      setEditId(r.id)
                      setShowForm(true)
                    }}>Edit</button>
                    <button className="text-red-600" onClick={async () => {
                      await fetch(`http://localhost:8080/api/recipes/${r.id}`, { method: 'DELETE' })
                      setRecipes(prev => prev.filter(x => x.id !== r.id))
                    }}>Delete</button>
                  </div>
                </li>
              ))}
            </ul>
            <div className="mt-4 flex items-center gap-3">
              <button disabled={page<=0} onClick={() => setPage(p => Math.max(p-1,0))} className="border rounded px-2 py-1 disabled:opacity-50">Prev</button>
              <span className="text-sm">Page {page+1} of {Math.max(1, Math.ceil(total/size))}</span>
              <button disabled={(page+1)*size>=total} onClick={() => setPage(p => p+1)} className="border rounded px-2 py-1 disabled:opacity-50">Next</button>
            </div>
          </>
        )}
      </section>
    </div>
  )
}

const ingredientSchema = z.object({
  name: z.string().min(1),
  quantity: z.number().positive().optional(),
  unit: z.string().optional(),
  notes: z.string().optional(),
})

const recipeSchema = z.object({
  name: z.string().min(1),
  servings: z.number().min(1),
  prepMinutes: z.number().int().min(0).optional(),
  cookMinutes: z.number().int().min(0).optional(),
  tags: z.array(z.string()).optional(),
  ingredients: z.array(ingredientSchema).optional(),
  instructionsMd: z.string().optional(),
})

type RecipeFormData = z.infer<typeof recipeSchema>

function RecipeForm({ value, onChange, onSubmit, onCancel, mode }: { value: RecipeCreate, onChange: (v: RecipeCreate) => void, onSubmit: () => void, onCancel: () => void, mode: 'create' | 'edit' }) {
  const { register, handleSubmit, control, formState: { errors }, setValue } = useForm<RecipeFormData>({
    resolver: zodResolver(recipeSchema),
    defaultValues: { ...value, ingredients: value.ingredients ?? [] }
  })
  const { fields, append, remove } = useFieldArray({ control, name: 'ingredients' })

  const onValid = (data: RecipeFormData) => {
    onChange(data as RecipeCreate)
    onSubmit()
  }

  return (
    <form onSubmit={handleSubmit(onValid)} className="border rounded p-4 space-y-3">
      <div className="grid grid-cols-1 md:grid-cols-3 gap-3">
        <div>
          <label className="block text-sm">Name</label>
          <input className="border rounded px-2 py-1 w-full" {...register('name')} />
          {errors.name && <p className="text-red-600 text-sm">Name is required</p>}
        </div>
        <div>
          <label className="block text-sm">Servings</label>
          <input type="number" min={1} className="border rounded px-2 py-1 w-full" {...register('servings', { valueAsNumber: true })} />
          {errors.servings && <p className="text-red-600 text-sm">Servings must be at least 1</p>}
        </div>
        <div>
          <label className="block text-sm">Prep minutes</label>
          <input type="number" min={0} className="border rounded px-2 py-1 w-full" {...register('prepMinutes', { valueAsNumber: true })} />
        </div>
        <div>
          <label className="block text-sm">Cook minutes</label>
          <input type="number" min={0} className="border rounded px-2 py-1 w-full" {...register('cookMinutes', { valueAsNumber: true })} />
        </div>
        <div>
          <label className="block text-sm">Tags (comma separated)</label>
          <input className="border rounded px-2 py-1 w-full" onChange={e => setValue('tags', e.target.value.split(',').map(s => s.trim()).filter(Boolean))} />
        </div>
      </div>

      <div>
        <label className="block text-sm mb-1">Ingredients</label>
        <div className="space-y-2">
          {fields.map((field, index) => (
            <div key={field.id} className="flex gap-2 items-center">
              <input className="border rounded px-2 py-1" placeholder="name" {...register(`ingredients.${index}.name` as const)} />
              <input className="border rounded px-2 py-1 w-24" placeholder="qty" inputMode="numeric" {...register(`ingredients.${index}.quantity` as const, { valueAsNumber: true })} />
              <input className="border rounded px-2 py-1 w-24" placeholder="unit" {...register(`ingredients.${index}.unit` as const)} />
              <button type="button" className="text-red-600" onClick={() => remove(index)}>Remove</button>
            </div>
          ))}
          <button type="button" className="border rounded px-3 py-1" onClick={() => append({ name: '' })}>Add ingredient</button>
        </div>
      </div>

      <div>
        <label className="block text-sm">Instructions</label>
        <textarea className="border rounded px-2 py-1 w-full h-28" {...register('instructionsMd')} />
      </div>

      <div className="flex gap-2">
        <button className="border rounded px-3 py-1 bg-sky-600 text-white" type="submit">{mode === 'edit' ? 'Update' : 'Save'}</button>
        <button className="border rounded px-3 py-1" type="button" onClick={onCancel}>Cancel</button>
      </div>
    </form>
  )
}
