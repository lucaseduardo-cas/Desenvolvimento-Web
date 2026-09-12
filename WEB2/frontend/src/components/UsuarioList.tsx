import { useEffect, useState } from "react";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";

export default function UsuarioList() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState<string | null>(null);

  useEffect(() => {
    api.get<Usuario[]>("/api/usuarios")
      .then((resposta) => {
        setUsuarios(resposta.data);
        setLoading(false);
      })
      .catch(() => {
        setErro("Não foi possível carregar os usuários. Verifique se o backend está ligado.");
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Carregando usuários...</p>;
  if (erro) return <p style={{ color: "red" }}>{erro}</p>;

  return (
    <div>
      <h2>Usuários Cadastrados</h2>
      {usuarios.length === 0 ? (
        <p>Nenhum usuário cadastrado.</p>
      ) : (
        <ul>
          {usuarios.map((u) => (
            <li key={u.id}>
              <strong>{u.nome}</strong> ({u.username}) – {u.email}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}