void main() {
  int n = 10;
  int serviciosDiarios = 3;
  int i = 0;
  while (i <= n) {
    i++;
    print("Día $i: $serviciosDiarios servicios = ${i * serviciosDiarios} total");
  }
  
  i = 1;
  do {
    print("Acumulado día $i: $serviciosDiarios * $i = ${i * serviciosDiarios}");
    i++;
  } while (i < n);
}