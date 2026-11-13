import { Link } from "react-router-dom";
import { temPapel } from "../services/auth";

const botaoLink = "rounded-md px-4 py-2 text-white";

export default function Home() {
  return (
    <div className="rounded-lg bg-white p-8 text-center shadow">
      <h1 className="mb-2 text-3xl font-bold text-blue-600">Bem-vindo ao sistema!</h1>
      <p className="mb-6 text-gray-600">Gerencie usuários, permissões e veículos.</p>
      <div className="flex flex-wrap justify-center gap-3">
        {temPapel("ADMIN") && (
          <>
            <Link to="/usuarios" className={`${botaoLink} bg-blue-600 hover:bg-blue-700`}>
              Usuários
            </Link>
            <Link to="/atribuir-permissoes" className={`${botaoLink} bg-gray-600 hover:bg-gray-700`}>
              Permissões
            </Link>
          </>
        )}
        <Link to="/carros" className={`${botaoLink} bg-gray-600 hover:bg-gray-700`}>
          Veículos
        </Link>
      </div>
    </div>
  );
}
