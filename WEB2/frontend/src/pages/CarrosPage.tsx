import { useEffect, useState } from "react";
import api from "../services/api";
import type { Carro } from "../types/Carro";
import CarroForm from "../components/CarroForm";

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
    <section>
      <h2>Veículos (Oficina)</h2>
      <CarroForm
        key={editando?.id ?? "novo"}
        carroEditando={editando}
        onCarroSalvo={() => {
          carregarCarros();
          setEditando(null);
        }}
        onCancelar={() => setEditando(null)}
      />
      <ul>
        {carros.map((c) => (
          <li key={c.id} style={{ marginBottom: "6px" }}>
            <strong>{c.marca} {c.modelo}</strong> ({c.ano}) – Placa: {c.placa}{" "}
            <button onClick={() => setEditando(c)}>Editar</button>{" "}
            <button onClick={() => excluir(c.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </section>
  );
}