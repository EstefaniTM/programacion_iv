import kotlin.text.toIntOrNull
import kotlin.text.uppercase

// No necesitas el import 'java.sql.DriverManager.println'.
// El import 'kotlin.io.println' es implícito y no hace falta escribirlo.

fun main() {
    println("--- Calculadora ---")

    // 1. Pedir el primer número
    println("Introduce el primer valor:")
    val value1: Int = readLine()?.toIntOrNull() ?: 0

    // 2. Pedir la operación como TEXTO
    println("¿Qué operación quieres hacer? (Escribe: Suma, Resta, Multiplicacion, Division)")
    val operacion: String = readLine() ?: "" // Se guarda como String

    // 3. Pedir el segundo número
    println("Introduce el segundo valor:")
    val value2: Int = readLine()?.toIntOrNull() ?: 0

    // 4. Usar 'when' para DECIDIR qué operación hacer y mostrar el resultado
    println("\n--- Resultado ---") // Un poco de formato para que se vea mejor

    when (operacion.uppercase()) { // .uppercase() ignora si el usuario usa mayúsculas o minúsculas
        "SUMA" -> {
            // Realiza el cálculo DENTRO de las llaves
            println("Suma: ${value1 + value2}")
        }
        "RESTA" -> {
            println("Resta: ${value1 - value2}")
        }
        "MULTIPLICACION" -> {
            println("Multiplicación: ${value1 * value2}")
        }
        "DIVISION" -> {
            if (value2 == 0) {
                println("Error: ¡No se puede dividir por cero!")
            } else {
                println("División: ${value1 / value2}")
            }
        }
        else -> {
            // Mensaje de error si el usuario escribe algo inválido
            println("Operación no válida. Por favor, reinicia y elige una correcta.")
        }
    }
}
