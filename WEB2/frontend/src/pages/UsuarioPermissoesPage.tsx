import { useEffect, useState } from "react";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";
import type { Permissao } from "../types/Permissao";

export default function UsuarioPermissoesPage() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [permissoes, setPermissoes] = useState<Permissao[]>([]);
  const [usuarioId, setUsuarioId] = useState<number | null>(null);
  const [idsSelecionados, setIdsSelecionados] = useState<number[]>([]);

  useEffect(() => {
    api.get<Usuario[]>("/usuarios").then((r) => setUsuarios(r.data));
    api.get<Permissao[]>("/permissoes").then((r) => setPermissoes(r.data));
  }, []);

  function selecionarUsuario(id: number) {
    setUsuarioId(id);
    if (!id) {
      setIdsSelecionados([]);
      return;
    }
    api.get<Usuario>(`/usuarios/${id}/permissoes`).then((r) => {
      setIdsSelecionados(r.data.permissoes?.map((p) => p.id) ?? []);
    });
  }

  function alternarPermissao(id: number) {
    setIdsSelecionados((prev) =>
      prev.includes(id) ? prev.filter((p) => p !== id) : [...prev, id]
    );
  }

  async function salvar() {
    if (usuarioId === null) return;
    try {
      await api.put(`/usuarios/${usuarioId}/permissoes`, idsSelecionados);
      alert("Permissões salvas com sucesso!");
    } catch {
      alert("Erro ao salvar permissões.");
    }
  }

  return (
    <div style={{ padding: "20px" }}>
      <h1>Atribuir Permissões</h1>

      <div style={{ marginBottom: "16px" }}>
        <label style={{ display: "block", marginBottom: "6px" }}>Usuário:</label>
        <select
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
      </div>

      <fieldset style={{ marginBottom: "16px", padding: "12px" }}>
        <legend>Permissões</legend>
        {permissoes.map((p) => (
          <label key={p.id} style={{ display: "block", margin: "6px 0" }}>
            <input
              type="checkbox"
              checked={idsSelecionados.includes(p.id)}
              onChange={() => alternarPermissao(p.id)}
            />{" "}
            <strong>{p.nome}</strong>: {p.descricao}
          </label>
        ))}
      </fieldset>

      <button onClick={salvar} disabled={usuarioId === null}>
        Salvar permissões
      </button>
    </div>
  );
}