
void main(){
    int meses = 10;
    double crecimiento = 1;

    for (int i=1;i<=meses;i++){
        crecimiento = crecimiento * i;
    }
    print("Crecimiento después de $meses meses: $crecimiento");
}