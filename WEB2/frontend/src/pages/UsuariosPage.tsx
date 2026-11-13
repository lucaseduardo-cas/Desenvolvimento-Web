import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";
import UsuarioForm from "../components/UsuarioForm";
import Botao from "../components/Botao";

export default function UsuariosPage() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [editando, setEditando] = useState<Usuario | null>(null);

  function carregarUsuarios() {
    api.get<Usuario[]>("/usuarios").then((res) => setUsuarios(res.data));
  }

  useEffect(() => {
    carregarUsuarios();
  }, []);

  async function excluir(id: number) {
    await api.delete(`/usuarios/${id}`);
    carregarUsuarios();
  }

  return (
    <div>
      <h1 className="mb-4 text-2xl font-bold text-gray-800">Usuários</h1>

      <UsuarioForm
        key={editando?.id ?? "novo"}
        usuarioEditando={editando}
        onUsuarioSalvo={() => {
          carregarUsuarios();
          setEditando(null);
        }}
        onCancelar={() => setEditando(null)}
      />

      <ul className="divide-y divide-gray-200 rounded-lg bg-white shadow">
        {usuarios.map((usuario) => (
          <li key={usuario.id} className="flex items-center justify-between gap-4 p-4">
            <div>
              <Link
                to={`/usuarios/${usuario.id}`}
                className="font-medium text-blue-600 hover:underline"
              >
                {usuario.nome} <span className="text-gray-500">({usuario.username})</span>
              </Link>
              <p className="text-sm text-gray-500">{usuario.email}</p>
            </div>
            <div className="flex gap-2">
              <Botao variante="neutro" onClick={() => setEditando(usuario)}>Editar</Botao>
              <Botao variante="perigo" onClick={() => excluir(usuario.id)}>Excluir</Botao>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}
