void main() {
  print("Registro de Servicios Funerarios");
  final servicios = [1, 2, 3, 3, 5, 5, 5, 7, 7, 8, 9, 10];
  print("Lista original de servicios $servicios");
  print("Total de servicios ${servicios.length}");
  print("Servicio en posición 4 ${servicios[4]}");
  print("Primer Servicio ${servicios.first}");
  final serviciosInvertidos = servicios.reversed;
  print("Orden inverso de servicios ${serviciosInvertidos}");
  print("Lista : ${serviciosInvertidos.toList()}");
  print("Conjunto único : ${serviciosInvertidos.toSet()}");
}