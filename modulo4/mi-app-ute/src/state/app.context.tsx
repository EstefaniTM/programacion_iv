import type { ReactNode } from "react";
import { createContext, useMemo, useReducer } from "react";
import { appReducer, initialAppState } from "@/state/app.reducer";
import type { AppAction, AppState } from "@/state/app.types";

const AppStateContext = createContext<AppState | null>(null);
const AppDispatchContext = createContext<React.Dispatch<AppAction> | null>(null);

type Props = {
  children: ReactNode;
};

export function AppProvider({ children }: Props) {
  const [state, dispatch] = useReducer(appReducer, initialAppState);

  const memoState = useMemo(() => state, [state]);

  return (
    <AppStateContext.Provider value={memoState}>
      <AppDispatchContext.Provider value={dispatch}>
        {children}
      </AppDispatchContext.Provider>
    </AppStateContext.Provider>
  );
}

export { AppStateContext, AppDispatchContext };