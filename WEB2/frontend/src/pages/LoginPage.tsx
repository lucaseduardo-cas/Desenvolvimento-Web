import { type FormEvent, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import api from "../services/api";
import { obterToken, salvarSessao, type Sessao } from "../services/auth";
import Botao from "../components/Botao";

const campo =
  "mb-3 w-full rounded-md border border-gray-300 px-3 py-2 focus:border-blue-500 focus:outline-none";

export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const navigate = useNavigate();

  if (obterToken()) {
    return <Navigate to="/" replace />;
  }

  async function entrar(e: FormEvent) {
    e.preventDefault();
    try {
      const resposta = await api.post<Sessao>("/login", { username, senha });
      salvarSessao(resposta.data);
      navigate("/");
    } catch {
      setErro("Usuário ou senha inválidos");
    }
  }

  return (
    <div className="min-h-screen bg-gray-100 px-4 pt-16">
      <form onSubmit={entrar} className="mx-auto max-w-sm rounded-lg bg-white p-6 shadow">
        <h1 className="mb-1 text-2xl font-bold text-gray-800">Entrar</h1>
        <p className="mb-4 text-sm text-gray-500">Acesse o sistema com seu usuário e senha.</p>
        <input
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Usuário"
          className={campo}
          autoFocus
          required
        />
        <input
          type="password"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          placeholder="Senha"
          className={campo}
          required
        />
        {erro && <p className="mb-3 text-sm text-red-600">{erro}</p>}
        <Botao type="submit" className="w-full py-2">Entrar</Botao>
      </form>
    </div>
  );
}
