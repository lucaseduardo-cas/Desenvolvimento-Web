import { useEffect, useState } from "react";
import api from "../services/api";
import type { Permissao } from "../types/Permissao";

export default function PermissaoList() {
  const [permissoes, setPermissoes] = useState<Permissao[]>([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState<string | null>(null);

  useEffect(() => {
    api.get<Permissao[]>("/permissoes")
      .then((resposta) => {
        setPermissoes(resposta.data);
        setLoading(false);
      })
      .catch(() => {
        setErro("Erro ao buscar permissões.");
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Carregando permissões...</p>;
  if (erro) return <p style={{ color: "red" }}>{erro}</p>;

  return (
    <div>
      <h2>Permissões</h2>
      {permissoes.length === 0 ? (
        <p>Nenhuma permissão cadastrada.</p>
      ) : (
        <ul>
          {permissoes.map((p) => (
            <li key={p.id}>
              <strong>{p.nome}</strong>: {p.descricao}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}