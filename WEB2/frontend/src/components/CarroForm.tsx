import { type FormEvent, useState, useEffect } from "react";
import api from "../services/api";
import type { Carro } from "../types/Carro";

interface CarroFormProps {
  onCarroSalvo: () => void;
  carroEditando?: Carro | null;
  onCancelar?: () => void;
}

export default function CarroForm({ onCarroSalvo, carroEditando, onCancelar }: CarroFormProps) {
  const [marca, setMarca] = useState(carroEditando?.marca ?? "");
  const [modelo, setModelo] = useState(carroEditando?.modelo ?? "");
  const [ano, setAno] = useState(carroEditando?.ano ? String(carroEditando.ano) : "");
  const [placa, setPlaca] = useState(carroEditando?.placa ?? "");

  // Atualiza os campos caso o carro em edição mude externamente
  useEffect(() => {
    setMarca(carroEditando?.marca ?? "");
    setModelo(carroEditando?.modelo ?? "");
    setAno(carroEditando?.ano ? String(carroEditando.ano) : "");
    setPlaca(carroEditando?.placa ?? "");
  }, [carroEditando]);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    const dados = { marca, modelo, ano: Number(ano), placa };

    try {
      if (carroEditando) {
        await api.put(`/carros/${carroEditando.id}`, dados);
      } else {
        await api.post("/carros", dados);
        // Limpa os campos após cadastrar
        setMarca("");
        setModelo("");
        setAno("");
        setPlaca("");
      }
      onCarroSalvo();
    } catch (error) {
      alert("Não foi possível salvar o veículo! Verifique se a placa já está cadastrada.");
    }
  }

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "15px", display: "flex", gap: "8px" }}>
      <input value={marca} onChange={(e) => setMarca(e.target.value)} placeholder="Marca" required />
      <input value={modelo} onChange={(e) => setModelo(e.target.value)} placeholder="Modelo" required />
      <input type="number" value={ano} onChange={(e) => setAno(e.target.value)} placeholder="Ano" required />
      <input value={placa} onChange={(e) => setPlaca(e.target.value)} placeholder="Placa" required />
      <button type="submit">{carroEditando ? "Salvar" : "Cadastrar"}</button>
      {carroEditando && <button type="button" onClick={onCancelar}>Cancelar</button>}
    </form>
  );
}