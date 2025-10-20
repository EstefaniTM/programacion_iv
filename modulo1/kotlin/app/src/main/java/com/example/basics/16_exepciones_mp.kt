fun main() {
    try {
        val resultado = 10 / 0
        println(resultado)
    } catch (e: Exception) {
        println("¡Ocurrió un error: $e")
    }
}