void main() {
  print("Maps");
  final Map<String, dynamic> cliente = {
    'name': 'Jose Luis',
    'edad': '34',
    'estaVivo': false,
    'materiales': <String>['velas'],
    'sprites': {1: 'src/ditto1.jpg', 2: 'src/ditto1.jpg'},
  };
  print(cliente);
  print(cliente['name']);
  print(cliente['sprites']);
  print(cliente['isAlive']);
}
