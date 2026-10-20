import { NavLink, Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <div style={{ fontFamily: "sans-serif" }}>
      <header style={{ background: "#1f2937", padding: "12px 24px", marginBottom: "20px" }}>
        <nav style={{ display: "flex", gap: "16px" }}>
          <NavLink
            to="/"
            style={({ isActive }) => ({
              color: isActive ? "#38bdf8" : "#ffffff",
              textDecoration: "none",
              fontWeight: isActive ? "bold" : "normal",
            })}
          >
            Home
          </NavLink>
          <NavLink
            to="/usuarios"
            style={({ isActive }) => ({
              color: isActive ? "#38bdf8" : "#ffffff",
              textDecoration: "none",
              fontWeight: isActive ? "bold" : "normal",
            })}
          >
            Usuários
          </NavLink>
          <NavLink
            to="/atribuir-permissoes"
            style={({ isActive }) => ({
              color: isActive ? "#38bdf8" : "#ffffff",
              textDecoration: "none",
              fontWeight: isActive ? "bold" : "normal",
            })}
          >
            Atribuir Permissões
          </NavLink>
        </nav>
      </header>

      <main style={{ padding: "0 24px" }}>
        <Outlet />
      </main>
    </div>
  );
}