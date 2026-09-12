import { useEffect, useState } from "react";
import api from "../services/api";
import type { Carro } from "../types/Carro";

export default function CarroList() {
  const [carros, setCarros] = useState<Carro[]>([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState<string | null>(null);

  useEffect(() => {
    api.get<Carro[]>("/carros")
      .then((resposta) => {
        setCarros(resposta.data);
        setLoading(false);
      })
      .catch(() => {
        setErro("Erro ao buscar carros.");
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Carregando carros...</p>;
  if (erro) return <p style={{ color: "red" }}>{erro}</p>;

  return (
    <div>
      <h2>Carros (Oficina)</h2>
      {carros.length === 0 ? (
        <p>Nenhum carro cadastrado.</p>
      ) : (
        <ul>
          {carros.map((c) => (
            <li key={c.id}>
              <strong>{c.marca} {c.modelo}</strong> — Ano: {c.ano}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}