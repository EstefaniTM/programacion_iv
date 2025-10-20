void main() {
  int n = 10;
  int serviciosPorDia = 3;
  for (int i = 1; i <= n; i++) 
    print("Servicios del día $i: $serviciosPorDia * $i = ${i * serviciosPorDia}");
}