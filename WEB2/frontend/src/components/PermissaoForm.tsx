import { type FormEvent, useState } from "react";
import api from "../services/api";
import type { Permissao } from "../types/Permissao";
import Botao from "./Botao";

const campo =
  "rounded-md border border-gray-300 px-3 py-2 focus:border-blue-500 focus:outline-none";

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
    <form
      onSubmit={handleSubmit}
      className="mb-6 grid gap-3 rounded-lg bg-white p-4 shadow md:grid-cols-3"
    >
      <input className={campo} value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome (ex: ROLE_ADMIN)" required />
      <input className={campo} value={descricao} onChange={(e) => setDescricao(e.target.value)} placeholder="Descrição" required />
      <div className="flex gap-2">
        <Botao type="submit" className="flex-1">
          {permissaoEditando ? "Salvar alterações" : "Cadastrar"}
        </Botao>
        {permissaoEditando && (
          <Botao type="button" variante="neutro" onClick={onCancelar}>
            Cancelar
          </Botao>
        )}
      </div>
    </form>
  );
}