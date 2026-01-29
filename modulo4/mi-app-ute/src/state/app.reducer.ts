import type { AppAction, AppState } from "@/state/app.types";

export const initialAppState: AppState = {
  counter: 0,
};

export function appReducer(state: AppState, action: AppAction): AppState {
  switch (action.type) {
    case "COUNTER/INC": {
      const step = action.payload ?? 1;
      return { ...state, counter: state.counter + step };
    }
    case "COUNTER/DEC": {
      const step = action.payload ?? 1;
      return { ...state, counter: state.counter - step };
    }
    case "COUNTER/RESET": {
      return { ...state, counter: 0 };
    }
    default: {
      // Exhaustiveness check (por seguridad en TS)
      const _exhaustive: never = action;
      return state;
    }
  }
}