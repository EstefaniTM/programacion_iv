void main() {
  print("Tipos de variables en Dart");
  final String pokemon = "Ditto";
  print(pokemon);
  
  String myName = 'francisco';
  myName = "Higuera";
  print(myName);
  const String elemento = "Fuego";
  print("elemento: $elemento");
  bool active = false;
  active = false;
  print("Es activo: $active");
  int hp= 1000;
  print("Caballos de fuerza $hp");
  List<String> abilities = ["impostor", "correlacion"];
    print("habilidades $abilities");
  
  final sprites = <String>["src/imagen1.jpg", "imagen2"];
  print("Imagenes $sprites");
  
  print("Impresión de varias lineas");
  print("""
  $pokemon
  $sprites
  """);
}
