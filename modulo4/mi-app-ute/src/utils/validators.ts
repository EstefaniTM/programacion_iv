export function isValidEmail(email: string): boolean {
  const v = email.trim().toLowerCase();
  // Regex simple (suficiente para práctica)
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v);
}

export function minLen(value: string, n: number): boolean {
  return value.trim().length >= n;
}