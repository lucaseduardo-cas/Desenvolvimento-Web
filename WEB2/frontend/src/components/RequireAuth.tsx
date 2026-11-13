import type { ReactNode } from "react";
import { Navigate } from "react-router-dom";
import { obterToken, temPapel } from "../services/auth";

interface RequireAuthProps {
  children: ReactNode;
  papel?: string; // se informado, exige também esse papel (ex.: "ADMIN")
}

export default function RequireAuth({ children, papel }: RequireAuthProps) {
  if (!obterToken()) {
    return <Navigate to="/login" replace />;
  }
  if (papel && !temPapel(papel)) {
    return <Navigate to="/" replace />;
  }
  return children;
}
