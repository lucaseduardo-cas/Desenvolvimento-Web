import axios from "axios";
import { obterToken, sair } from "./auth";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
});

// Envia o token JWT em todas as requisições
api.interceptors.request.use((config) => {
  const token = obterToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Token ausente/expirado (401): limpa a sessão e volta para o login
api.interceptors.response.use(
  (resposta) => resposta,
  (erro) => {
    const ehLogin = erro.config?.url === "/login";
    if (erro.response?.status === 401 && !ehLogin) {
      sair();
      window.location.href = "/login";
    }
    return Promise.reject(erro);
  }
);

export default api;
