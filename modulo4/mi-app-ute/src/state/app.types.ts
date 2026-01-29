export type AppState = {
  counter: number;
};

export type AppAction =
  | { type: "COUNTER/INC"; payload?: number }
  | { type: "COUNTER/DEC"; payload?: number }
  | { type: "COUNTER/RESET" };