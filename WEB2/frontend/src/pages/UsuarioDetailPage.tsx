import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../services/api";
import type { Usuario } from "../types/Usuario";

export default function UsuarioDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [usuario, setUsuario] = useState<Usuario | null>(null);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    api
      .get<Usuario>(`/usuarios/${id}`)
      .then((resposta) => {
        setUsuario(resposta.data);
      })
      .catch((erro) => {
        console.error("Erro ao carregar detalhes do usuário:", erro);
      })
      .finally(() => {
        setCarregando(false);
      });
  }, [id]);

  if (carregando) {
    return <p>A carregar detalhes...</p>;
  }

  if (!usuario) {
    return (
      <div>
        <p>Usuário não encontrado.</p>
        <button onClick={() => navigate("/usuarios")}>Voltar</button>
      </div>
    );
  }

  return (
    <div>
      <h1>{usuario.nome}</h1>
      <p>
        <strong>Username:</strong> {usuario.username}
      </p>
      <p>
        <strong>E-mail:</strong> {usuario.email}
      </p>
      <button onClick={() => navigate("/usuarios")} style={{ marginTop: "12px" }}>
        Voltar
      </button>
    </div>
  );
}