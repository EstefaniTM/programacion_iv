void main() {
  final Servicio servicioBasico = Servicio(nombre: 'Funeral Básico', caracteristica: 'urna estándar');
  print(servicioBasico);
  print(servicioBasico.nombre);
  print(servicioBasico.caracteristica);
}

class Servicio {
  String nombre = "";
  String caracteristica = "";
  Servicio({required this.nombre, this.caracteristica = "sin características"});

  @override
  String toString() {
    return "$nombre - $caracteristica";
  }
}