import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div>
      <h1>Bem-vindo ao sistema!</h1>
      <p>Selecione uma das opções abaixo para navegar:</p>
      <div style={{ marginTop: "12px" }}>
        <Link to="/usuarios" style={{ marginRight: "12px", color: "#38bdf8" }}>
          Ir para Usuários
        </Link>
        {" | "}
        <Link to="/atribuir-permissoes" style={{ marginLeft: "12px", color: "#38bdf8" }}>
          Atribuir Permissões
        </Link>
      </div>
    </div>
  );
}