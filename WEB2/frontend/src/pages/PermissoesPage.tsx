import { useEffect, useState } from "react";
import { Link } from "react-router-dom"; // 1. Adiciona a importação do Link
import api from "../services/api";
import type { Permissao } from "../types/Permissao";
import PermissaoForm from "../components/PermissaoForm";

export default function PermissoesPage() {
  const [permissoes, setPermissoes] = useState<Permissao[]>([]);
  const [editando, setEditando] = useState<Permissao | null>(null);

  function carregarPermissoes() {
    api.get<Permissao[]>("/permissoes").then((res) => setPermissoes(res.data));
  }

  useEffect(() => {
    carregarPermissoes();
  }, []);

  async function excluir(id: number) {
    await api.delete(`/permissoes/${id}`);
    carregarPermissoes();
  }

  return (
    <section>
      <h2>Permissões de Acesso</h2>
      <PermissaoForm
        key={editando?.id ?? "novo"}
        permissaoEditando={editando}
        onPermissaoSalva={() => {
          carregarPermissoes();
          setEditando(null);
        }}
        onCancelar={() => setEditando(null)}
      />
      <ul>
        {permissoes.map((p) => (
          <li key={p.id} style={{ marginBottom: "6px" }}>
            {/* 2. Link adicionado no nome para navegar até o detalhe */}
            <Link to={`/permissoes/${p.id}`} style={{ marginRight: "8px", textDecoration: "none" }}>
              <strong>{p.nome}</strong>
            </Link>
            : {p.descricao}{" "}
            <button onClick={() => setEditando(p)}>Editar</button>{" "}
            <button onClick={() => excluir(p.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </section>
  );
}