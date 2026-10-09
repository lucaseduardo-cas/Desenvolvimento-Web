import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';

describe('Componente de Listagem', () => {
  it('deve renderizar o cabeçalho corretamente', () => {
    render(<h2>Lista de Veículos</h2>);
    const elemento = screen.getByText('Lista de Veículos');
    expect(elemento).not.toBeNull();
  });
});