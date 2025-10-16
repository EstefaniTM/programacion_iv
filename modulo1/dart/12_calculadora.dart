class Hero{
  String name = "";
  String power = "";
  Hero({required this.name,this.power = "sin poder"});
  @override
  String toString(){
    return "$name - $power";
  }
}

class Calculadora{
  Calculadora();
  
  double sumar(double num1, double num2){
    return num1+num2;
  }
  
  double restar(double num1, double num2){
    return num1-num2;
  }
  
  double multiplicar(double num1, double num2){
    return num1*num2;
  }
  
  double dividir(double num1, double num2){
    return num1/num2;
  }
}

void main() {
  final Hero wolverine = Hero(name: "Logan", power: "Regeneración");
  print(wolverine);
  print(wolverine.name);
  print(wolverine.power);
  
  final Calculadora calc = Calculadora();
  print(calc.sumar(1,2));
  print(calc.restar(1,2));
  print(calc.multiplicar(1,2));
  print(calc.dividir(1,2));
}