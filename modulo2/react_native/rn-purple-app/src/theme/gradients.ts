export const gradientPurple = {
  // Use `as const` so TypeScript infers a readonly tuple of string literals,
  // matching the LinearGradient `colors` prop type (readonly [ColorValue, ColorValue, ...]).
  colors: ["#1d0b3a", "#240b57", "#3a0e86"] as const,
  start: { x: 0, y: 0 },
  end: { x: 1, y: 1 }
};

export const gradientPink = {
  colors: ["#3a0e86", "#7215a8", "#ff2fd8"] as const,
  start: { x: 0, y: 0 },
  end: { x: 1, y: 0 }
};