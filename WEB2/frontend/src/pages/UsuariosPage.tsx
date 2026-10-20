import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";
import UsuarioForm from "../components/UsuarioForm";

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
    <section>
      <h2>Colaboradores</h2>
      <UsuarioForm
        key={editando?.id ?? "novo"}
        usuarioEditando={editando}
        onUsuarioSalvo={() => {
          carregarUsuarios();
          setEditando(null);
        }}
        onCancelar={() => setEditando(null)}
      />
      <ul>
        {usuarios.map((u) => (
          <li key={u.id} style={{ marginBottom: "6px" }}>
            {/* Link para a rota dinâmica de detalhes */}
            <Link to={`/usuarios/${u.id}`} style={{ marginRight: "8px", textDecoration: "none" }}>
              <strong>{u.nome}</strong> ({u.username})
            </Link>
            – {u.email}{" "}
            <button onClick={() => setEditando(u)}>Editar</button>{" "}
            <button onClick={() => excluir(u.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </section>
  );
}