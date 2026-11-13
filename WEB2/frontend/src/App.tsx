import { Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./pages/Home";
import UsuariosPage from "./pages/UsuariosPage";
import UsuarioDetailPage from "./pages/UsuarioDetailPage";
import UsuarioPermissoesPage from "./pages/UsuarioPermissoesPage";
import PermissoesPage from "./pages/PermissoesPage";
import PermissaoDetailPage from "./pages/PermissaoDetailPage";
import CarrosPage from "./pages/CarrosPage";

export default function App() {
  return (
    <Routes>
      <Route element={<Layout />}>
        <Route path="/" element={<Home />} />
        <Route path="/usuarios" element={<UsuariosPage />} />
        <Route path="/usuarios/:id" element={<UsuarioDetailPage />} />
        <Route path="/permissoes" element={<PermissoesPage />} />
        <Route path="/permissoes/:id" element={<PermissaoDetailPage />} />
        <Route path="/atribuir-permissoes" element={<UsuarioPermissoesPage />} />
        <Route path="/carros" element={<CarrosPage />} />
        <Route
          path="*"
          element={<h1 className="text-2xl font-bold text-gray-800">404 - Página não encontrada</h1>}
        />
      </Route>
    </Routes>
  );
}