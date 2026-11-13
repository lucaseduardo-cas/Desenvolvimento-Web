import { type FormEvent, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";
import Botao from "./Botao";

const campo =
  "rounded-md border border-gray-300 px-3 py-2 focus:border-blue-500 focus:outline-none";

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
  const navigate = useNavigate();

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    const dados: Record<string, string> = { nome, username, email };
    if (senha) {
      dados.senha = senha;
    }

    if (usuarioEditando) {
      await api.put(`/usuarios/${usuarioEditando.id}`, dados);
    } else {
      await api.post("/usuarios", dados);
    }
    setSenha("");
    onUsuarioSalvo();
    navigate("/usuarios");
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="mb-6 grid gap-3 rounded-lg bg-white p-4 shadow md:grid-cols-5"
    >
      <input className={campo} value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Nome" required />
      <input className={campo} value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" required />
      <input className={campo} value={email} onChange={(e) => setEmail(e.target.value)} placeholder="E-mail" type="email" required />
      <input
        className={campo}
        value={senha}
        onChange={(e) => setSenha(e.target.value)}
        placeholder={usuarioEditando ? "Nova senha (opcional)" : "Senha"}
        type="password"
        required={!usuarioEditando}
      />
      <div className="flex gap-2">
        <Botao type="submit" className="flex-1">
          {usuarioEditando ? "Salvar alterações" : "Cadastrar"}
        </Botao>
        {usuarioEditando && (
          <Botao type="button" variante="neutro" onClick={onCancelar}>
            Cancelar
          </Botao>
        )}
      </div>
    </form>
  );
}