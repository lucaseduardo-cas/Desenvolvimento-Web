import { useEffect, useState } from "react";
import api from "../services/api";
import type { Permissao } from "../types/Permissao";
import PermissaoForm from "./PermissaoForm";

export default function PermissaoList() {
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
    <div>
      <h2>Permissões</h2>
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
            <strong>{p.nome}</strong>: {p.descricao}{" "}
            <button onClick={() => setEditando(p)}>Editar</button>{" "}
            <button onClick={() => excluir(p.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </div>
  );
}