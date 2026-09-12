import UsuarioList from "./components/UsuarioList";
import PermissaoList from "./components/PermissaoList";
import CarroList from "./components/CarroList";

export default function App() {
  return (
    <main style={{ padding: "24px", fontFamily: "sans-serif" }}>
      <h1>Painel Oficina - Programação Web II</h1>
      <hr />
      <UsuarioList />
      <hr />
      <PermissaoList />
      <hr />
      <CarroList />
    </main>
  );
}