import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../services/api";
import type { Permissao } from "../types/Permissao";

export default function PermissaoDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [permissao, setPermissao] = useState<Permissao | null>(null);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    api
      .get<Permissao>(`/permissoes/${id}`)
      .then((resposta) => {
        setPermissao(resposta.data);
      })
      .catch((erro) => {
        console.error("Erro ao carregar detalhes da permissão:", erro);
      })
      .finally(() => {
        setCarregando(false);
      });
  }, [id]);

  if (carregando) {
    return <p>Carregando detalhes...</p>;
  }

  if (!permissao) {
    return (
      <div>
        <p>Permissão não encontrada.</p>
        <button onClick={() => navigate("/permissoes")}>Voltar</button>
      </div>
    );
  }

  return (
    <div>
      <h1>{permissao.nome}</h1>
      <p><strong>Descrição:</strong> {permissao.descricao}</p>
      <button onClick={() => navigate("/permissoes")} style={{ marginTop: "12px" }}>
        Voltar
      </button>
    </div>
  );
}