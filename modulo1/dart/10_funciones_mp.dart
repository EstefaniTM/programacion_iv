void main(){
  print(mensajeInicial());
  print(calculoTotal(3,4));
  print(precioServicio(6,8));
  print(precioServicio(8));
  print(saludoPersonal(nombre:"Higuera", mensaje:"Nuestro pésame"));
}

String mensajeInicial() => 'Servicios Funerarios Familiares';
int calculoTotal(int servicioA, int servicioB) => servicioA + servicioB;
  
int precioServicio(int base, [int extra = 0]){
  return base + extra;
}

String saludoPersonal({required String nombre, String mensaje = "Acompañamos su dolor"}){
  return '$mensaje, $nombre';
}