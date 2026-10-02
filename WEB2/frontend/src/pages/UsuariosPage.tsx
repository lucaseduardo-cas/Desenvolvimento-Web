import { useEffect, useState } from "react";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";
import UsuarioForm from "../components/UsuarioForm";

export default function UsuariosPage() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [editando, setEditando] = useState<Usuario | null>(null);

  function carregarUsuarios() {
    api.get<Usuario[]>("/api/usuarios").then((res) => setUsuarios(res.data));
  }

  useEffect(() => {
    carregarUsuarios();
  }, []);

  async function excluir(id: number) {
    await api.delete(`/api/usuarios/${id}`);
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
            <strong>{u.nome}</strong> ({u.username}) – {u.email}{" "}
            <button onClick={() => setEditando(u)}>Editar</button>{" "}
            <button onClick={() => excluir(u.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </section>
  );
}