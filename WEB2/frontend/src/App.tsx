import CarrosPage from "./pages/CarrosPage";
import UsuariosPage from "./pages/UsuariosPage";
import PermissoesPage from "./pages/PermissoesPage";

export default function App() {
  return (
    <main style={{ padding: "24px", fontFamily: "sans-serif" }}>
      <h1>Painel Oficina - Programação Web II</h1>
      <hr />
      <CarrosPage />
      <hr />
      <UsuariosPage />
      <hr />
      <PermissoesPage />
    </main>
  );
}