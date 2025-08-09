import { describe, it, expect, vi } from 'vitest'
import { render, screen, fireEvent } from '@testing-library/react'
import App from '../App'

describe('App filters and form', () => {
  it('renders search input', () => {
    render(<App />)
    expect(screen.getByLabelText(/search/i)).toBeDefined()
  })

  it('opens form and validates required fields', async () => {
    render(<App />)
    fireEvent.click(screen.getByRole('button', { name: /new recipe/i }))
    // Try submit without name
    fireEvent.click(screen.getByRole('button', { name: /save|update/i }))
    expect(await screen.findByText(/name is required/i)).toBeDefined()
  })
})

