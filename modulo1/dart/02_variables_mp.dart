void main() {
  final String tipoAccion = "Velacion";
  print(tipoAccion);

  String nombreCliente = 'Maria de Jesus';
  nombreCliente = 'Jose Luis';

  const String lugarVelorio = "Rumipamba";
  print(lugarVelorio);

  bool active = false;
  active = false;
  print("Es activo: $active");

  int numero = 1000;
  print('$numero');

  List<String> materiales = ["velas", "ataud"];
  print("habilidades $materiales");

  final sprites = <String>["src/imagen1.jpg", "imagen2"];
  print("Imagenes $sprites");

  print("Impresión de varias lineas");
  print("""
  $numero
  $materiales
  """);
}
