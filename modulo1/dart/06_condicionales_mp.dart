void main() {
  int servicios = 2;
  if (servicios > 2){
    print('servicios > 2 is true');
  }
  if (servicios < 2){
    print('servicios < 2 is true');
  }
  if (servicios == 2) {
    print('servicios == 2 is true');
  }
  if (servicios > 2){
    print('servicios > 2 is true');
  } else {
    print('servicios <= 2 is true');
  }
  if (servicios == 2) {
    print('servicios == 2 is true');
  } else {
    print('servicios != 2 is true');
  }
  
  int contratos = 2;
  if (contratos > 2){
    print('contratos > 2 is true');
  } else if (contratos < 2){
    print('contratos == 2 is true');
  } else {
    print('contratos == 2 is true');
  }
  
  int capacidad = 18;
  String estadoCapacidad = capacidad >= 18 ? 'capacidad maxima': 'capacidad disponible';
  print(estadoCapacidad);
}