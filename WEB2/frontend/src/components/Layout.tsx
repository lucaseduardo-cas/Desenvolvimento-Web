import { NavLink, Outlet } from "react-router-dom";

const linkClass = ({ isActive }: { isActive: boolean }) =>
  `rounded-md px-3 py-2 text-sm font-medium transition ${
    isActive ? "bg-blue-800 text-white" : "text-blue-100 hover:bg-blue-500"
  }`;

export default function Layout() {
  return (
    <div className="min-h-screen bg-gray-100 text-gray-800">
      <nav className="bg-blue-600 shadow">
        <div className="mx-auto flex max-w-5xl flex-wrap items-center gap-2 px-4 py-3">
          <span className="mr-4 text-lg font-bold text-white">Sistema</span>
          <NavLink to="/" end className={linkClass}>Home</NavLink>
          <NavLink to="/usuarios" className={linkClass}>Usuários</NavLink>
          <NavLink to="/permissoes" className={linkClass}>Permissões</NavLink>
          <NavLink to="/atribuir-permissoes" className={linkClass}>Atribuir Permissões</NavLink>
          <NavLink to="/carros" className={linkClass}>Veículos</NavLink>
        </div>
      </nav>

      <main className="mx-auto max-w-5xl p-4">
        <Outlet />
      </main>
    </div>
  );
}