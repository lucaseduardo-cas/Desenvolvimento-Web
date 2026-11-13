import { Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import RequireAuth from "./components/RequireAuth";
import LoginPage from "./pages/LoginPage";
import Home from "./pages/Home";
import UsuariosPage from "./pages/UsuariosPage";
import UsuarioDetailPage from "./pages/UsuarioDetailPage";
import UsuarioPermissoesPage from "./pages/UsuarioPermissoesPage";
import PermissoesPage from "./pages/PermissoesPage";
import PermissaoDetailPage from "./pages/PermissaoDetailPage";
import CarrosPage from "./pages/CarrosPage";

const soAdmin = (pagina: React.ReactNode) => <RequireAuth papel="ADMIN">{pagina}</RequireAuth>;

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />

      <Route element={<RequireAuth><Layout /></RequireAuth>}>
        <Route path="/" element={<Home />} />
        <Route path="/carros" element={<CarrosPage />} />

        <Route path="/usuarios" element={soAdmin(<UsuariosPage />)} />
        <Route path="/usuarios/:id" element={soAdmin(<UsuarioDetailPage />)} />
        <Route path="/permissoes" element={soAdmin(<PermissoesPage />)} />
        <Route path="/permissoes/:id" element={soAdmin(<PermissaoDetailPage />)} />
        <Route path="/atribuir-permissoes" element={soAdmin(<UsuarioPermissoesPage />)} />

        <Route
          path="*"
          element={<h1 className="text-2xl font-bold text-gray-800">404 - Página não encontrada</h1>}
        />
      </Route>
    </Routes>
  );
}
