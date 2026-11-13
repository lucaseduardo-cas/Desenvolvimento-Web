import { type FormEvent, useState, useEffect } from "react";
import api from "../services/api";
import type { Carro } from "../types/Carro";
import Botao from "./Botao";

const campo =
  "rounded-md border border-gray-300 px-3 py-2 focus:border-blue-500 focus:outline-none";

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
    <form
      onSubmit={handleSubmit}
      className="mb-6 grid gap-3 rounded-lg bg-white p-4 shadow md:grid-cols-5"
    >
      <input className={campo} value={marca} onChange={(e) => setMarca(e.target.value)} placeholder="Marca" required />
      <input className={campo} value={modelo} onChange={(e) => setModelo(e.target.value)} placeholder="Modelo" required />
      <input className={campo} type="number" value={ano} onChange={(e) => setAno(e.target.value)} placeholder="Ano" required />
      <input className={campo} value={placa} onChange={(e) => setPlaca(e.target.value)} placeholder="Placa" required />
      <div className="flex gap-2">
        <Botao type="submit" className="flex-1">
          {carroEditando ? "Salvar alterações" : "Cadastrar"}
        </Botao>
        {carroEditando && (
          <Botao type="button" variante="neutro" onClick={onCancelar}>
            Cancelar
          </Botao>
        )}
      </div>
    </form>
  );
}