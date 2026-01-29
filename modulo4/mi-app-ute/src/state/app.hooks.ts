import { useContext } from "react";
import { AppDispatchContext, AppStateContext } from "@/state/app.context";

export function useAppState() {
  const ctx = useContext(AppStateContext);
  if (!ctx) throw new Error("useAppState debe usarse dentro de <AppProvider>");
  return ctx;
}

export function useAppDispatch() {
  const ctx = useContext(AppDispatchContext);
  if (!ctx) throw new Error("useAppDispatch debe usarse dentro de <AppProvider>");
  return ctx;
}