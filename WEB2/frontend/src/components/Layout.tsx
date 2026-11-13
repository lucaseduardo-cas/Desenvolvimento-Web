import { NavLink, Outlet, useNavigate } from "react-router-dom";
import { obterSessao, sair, temPapel } from "../services/auth";

const linkClass = ({ isActive }: { isActive: boolean }) =>
  `rounded-md px-3 py-2 text-sm font-medium transition ${
    isActive ? "bg-blue-800 text-white" : "text-blue-100 hover:bg-blue-500"
  }`;

export default function Layout() {
  const navigate = useNavigate();
  const sessao = obterSessao();

  function deslogar() {
    sair();
    navigate("/login");
  }

  return (
    <div className="min-h-screen bg-gray-100 text-gray-800">
      <nav className="bg-blue-600 shadow">
        <div className="mx-auto flex max-w-5xl flex-wrap items-center gap-2 px-4 py-3">
          <span className="mr-4 text-lg font-bold text-white">Sistema</span>
          <NavLink to="/" end className={linkClass}>Home</NavLink>
          {temPapel("ADMIN") && (
            <>
              <NavLink to="/usuarios" className={linkClass}>Usuários</NavLink>
              <NavLink to="/permissoes" className={linkClass}>Permissões</NavLink>
              <NavLink to="/atribuir-permissoes" className={linkClass}>Atribuir Permissões</NavLink>
            </>
          )}
          <NavLink to="/carros" className={linkClass}>Veículos</NavLink>

          <div className="ml-auto flex items-center gap-3">
            {sessao && <span className="text-sm text-blue-100">Olá, {sessao.nome}</span>}
            <button
              onClick={deslogar}
              className="cursor-pointer rounded-md bg-blue-800 px-3 py-2 text-sm font-medium text-white hover:bg-blue-900"
            >
              Sair
            </button>
          </div>
        </div>
      </nav>

      <main className="mx-auto max-w-5xl p-4">
        <Outlet />
      </main>
    </div>
  );
}
