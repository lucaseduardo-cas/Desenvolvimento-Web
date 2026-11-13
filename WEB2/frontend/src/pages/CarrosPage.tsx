import { useEffect, useState } from "react";
import api from "../services/api";
import type { Carro } from "../types/Carro";
import CarroForm from "../components/CarroForm";
import Botao from "../components/Botao";

export default function CarrosPage() {
  const [carros, setCarros] = useState<Carro[]>([]);
  const [editando, setEditando] = useState<Carro | null>(null);

  function carregarCarros() {
    api.get<Carro[]>("/carros").then((res) => setCarros(res.data));
  }

  useEffect(() => {
    carregarCarros();
  }, []);

  async function excluir(id: number) {
    await api.delete(`/carros/${id}`);
    carregarCarros();
  }

  return (
    <div>
      <h1 className="mb-4 text-2xl font-bold text-gray-800">Veículos (Oficina)</h1>

      <CarroForm
        key={editando?.id ?? "novo"}
        carroEditando={editando}
        onCarroSalvo={() => {
          carregarCarros();
          setEditando(null);
        }}
        onCancelar={() => setEditando(null)}
      />

      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
        {carros.map((c) => (
          <div key={c.id} className="flex flex-col justify-between rounded-lg bg-white p-4 shadow">
            <div className="mb-4">
              <h2 className="text-lg font-semibold text-gray-800">
                {c.marca} {c.modelo}
              </h2>
              <p className="text-sm text-gray-500">Ano: {c.ano}</p>
              <p className="text-sm text-gray-500">Placa: {c.placa}</p>
            </div>
            <div className="flex gap-2">
              <Botao variante="neutro" onClick={() => setEditando(c)}>Editar</Botao>
              <Botao variante="perigo" onClick={() => excluir(c.id)}>Excluir</Botao>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
