import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../services/api";
import type { Permissao } from "../types/Permissao";
import PermissaoForm from "../components/PermissaoForm";
import Botao from "../components/Botao";

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
    <div>
      <h1 className="mb-4 text-2xl font-bold text-gray-800">Permissões de Acesso</h1>

      <PermissaoForm
        key={editando?.id ?? "novo"}
        permissaoEditando={editando}
        onPermissaoSalva={() => {
          carregarPermissoes();
          setEditando(null);
        }}
        onCancelar={() => setEditando(null)}
      />

      <ul className="divide-y divide-gray-200 rounded-lg bg-white shadow">
        {permissoes.map((p) => (
          <li key={p.id} className="flex items-center justify-between gap-4 p-4">
            <div>
              <Link to={`/permissoes/${p.id}`} className="font-medium text-blue-600 hover:underline">
                {p.nome}
              </Link>
              <p className="text-sm text-gray-500">{p.descricao}</p>
            </div>
            <div className="flex gap-2">
              <Botao variante="neutro" onClick={() => setEditando(p)}>Editar</Botao>
              <Botao variante="perigo" onClick={() => excluir(p.id)}>Excluir</Botao>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}
