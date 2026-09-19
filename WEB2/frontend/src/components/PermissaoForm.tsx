import { FormEvent, useState } from "react";
import api from "../services/api";
import type { Permissao } from "../types/Permissao";

interface PermissaoFormProps {
  onPermissaoSalva: () => void;
  permissaoEditando?: Permissao | null;
  onCancelar?: () => void;
}

export default function PermissaoForm({ onPermissaoSalva, permissaoEditando, onCancelar }: PermissaoFormProps) {
  const [nome, setNome] = useState(permissaoEditando?.nome ?? "");
  const [descricao, setDescricao] = useState(permissaoEditando?.descricao ?? "");

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    const dados = { nome, descricao };
    if (permissaoEditando) {
      await api.put(`/permissoes/${permissaoEditando.id}`, dados);
    } else {
      await api.post("/permissoes", dados);
    }
    onPermissaoSalva();
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "15px", display: "flex", gap: "8px" }}>
      <input value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome (ex: ROLE_ADMIN)" required />
      <input value={descricao} onChange={(e) => setDescricao(e.target.value)} placeholder="Descrição" required />
      <button type="submit">{permissaoEditando ? "Salvar" : "Cadastrar"}</button>
      {permissaoEditando && <button type="button" onClick={onCancelar}>Cancelar</button>}
    </form>
  );
}