import { FormEvent, useState } from "react";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";

interface UsuarioFormProps {
  onUsuarioSalvo: () => void;
  usuarioEditando?: Usuario | null;
  onCancelar?: () => void;
}

export default function UsuarioForm({ onUsuarioSalvo, usuarioEditando, onCancelar }: UsuarioFormProps) {
  const [nome, setNome] = useState(usuarioEditando?.nome ?? "");
  const [username, setUsername] = useState(usuarioEditando?.username ?? "");
  const [email, setEmail] = useState(usuarioEditando?.email ?? "");
  const [senha, setSenha] = useState("");

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    const dados: Record<string, string> = { nome, username, email };
    if (senha) {
      dados.senha = senha;
    }

    if (usuarioEditando) {
      await api.put(`/api/usuarios/${usuarioEditando.id}`, dados);
    } else {
      await api.post("/api/usuarios", dados);
    }
    setSenha("");
    onUsuarioSalvo();
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "15px", display: "flex", gap: "8px" }}>
      <input value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome" required />
      <input value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" required />
      <input value={email} onChange={(e) => setEmail(e.target.value)} placeholder="E-mail" type="email" required />
      <input
        value={senha}
        onChange={(e) => setSenha(e.target.value)}
        placeholder={usuarioEditando ? "Nova senha (opcional)" : "Senha"}
        type="password"
        required={!usuarioEditando}
      />
      <button type="submit">{usuarioEditando ? "Salvar" : "Cadastrar"}</button>
      {usuarioEditando && <button type="button" onClick={onCancelar}>Cancelar</button>}
    </form>
  );
}