export type Product = {
  id: string;
  name: string;
  price: number;
  brand: string;
  description: string;
};

export const PRODUCTS: Product[] = [
  { id: "p1", name: "Mouse", price: 12, brand: "Logitech", description: "Mouse inalámbrico ergonómico" },
  { id: "p2", name: "Keyboard", price: 25, brand: "Razer", description: "Teclado mecánico RGB" },
  { id: "p3", name: "Monitor", price: 180, brand: "Samsung", description: "Monitor 24 pulgadas Full HD" },
  { id: "p4", name: "Laptop Stand", price: 30, brand: "Rain Design", description: "Soporte de aluminio para laptop" },
  { id: "p5", name: "Headset", price: 45, brand: "HyperX", description: "Audífonos gaming con micrófono" },
];
