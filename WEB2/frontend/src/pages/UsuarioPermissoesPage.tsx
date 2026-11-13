import { useEffect, useState } from "react";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";
import type { Permissao } from "../types/Permissao";
import Botao from "../components/Botao";

type Aviso = { tipo: "sucesso" | "erro"; texto: string } | null;

export default function UsuarioPermissoesPage() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [permissoes, setPermissoes] = useState<Permissao[]>([]);
  const [usuarioId, setUsuarioId] = useState<number | null>(null);
  const [idsSelecionados, setIdsSelecionados] = useState<number[]>([]);
  const [aviso, setAviso] = useState<Aviso>(null);

  useEffect(() => {
    api.get<Usuario[]>("/usuarios").then((r) => setUsuarios(r.data));
    api.get<Permissao[]>("/permissoes").then((r) => setPermissoes(r.data));
  }, []);

  function selecionarUsuario(id: number) {
    setAviso(null);
    if (!id) {
      setUsuarioId(null);
      setIdsSelecionados([]);
      return;
    }
    setUsuarioId(id);
    api.get<Usuario>(`/usuarios/${id}/permissoes`).then((r) => {
      setIdsSelecionados(r.data.permissoes?.map((p) => p.id) ?? []);
    });
  }

  function alternarPermissao(id: number) {
    setAviso(null);
    setIdsSelecionados((prev) =>
      prev.includes(id) ? prev.filter((p) => p !== id) : [...prev, id]
    );
  }

  async function salvar() {
    if (usuarioId === null) return;
    try {
      await api.put(`/usuarios/${usuarioId}/permissoes`, idsSelecionados);
      setAviso({ tipo: "sucesso", texto: "Permissões salvas!" });
    } catch {
      setAviso({ tipo: "erro", texto: "Erro ao salvar permissões." });
    }
  }

  return (
    <div className="rounded-lg bg-white p-6 shadow">
      <h1 className="mb-4 text-2xl font-bold text-gray-800">Atribuir Permissões</h1>

      {aviso && (
        <div
          className={`mb-4 rounded-md px-4 py-2 ${
            aviso.tipo === "sucesso" ? "bg-green-100 text-green-800" : "bg-red-100 text-red-800"
          }`}
        >
          {aviso.texto}
        </div>
      )}

      <label className="mb-1 block text-sm font-medium text-gray-700">Usuário</label>
      <select
        className="mb-4 w-full rounded-md border border-gray-300 px-3 py-2"
        value={usuarioId ?? ""}
        onChange={(e) => selecionarUsuario(Number(e.target.value))}
      >
        <option value="">Selecione...</option>
        {usuarios.map((u) => (
          <option key={u.id} value={u.id}>
            {u.nome} ({u.username})
          </option>
        ))}
      </select>

      <fieldset className="mb-4 rounded-md border border-gray-300 p-4">
        <legend className="px-2 text-sm font-medium text-gray-700">Permissões</legend>
        {permissoes.map((p) => (
          <label key={p.id} className="flex items-center gap-2 py-1">
            <input
              type="checkbox"
              checked={idsSelecionados.includes(p.id)}
              onChange={() => alternarPermissao(p.id)}
            />
            <span>
              <strong>{p.nome}</strong> — {p.descricao}
            </span>
          </label>
        ))}
      </fieldset>

      <Botao variante="sucesso" onClick={salvar} disabled={usuarioId === null}>
        Salvar permissões
      </Botao>
    </div>
  );
}
