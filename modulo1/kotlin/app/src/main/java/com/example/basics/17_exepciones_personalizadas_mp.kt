class ServicioNoDisponibleException(message: String) : Exception(message)

fun realizarServicio(tipoServicio: String): String {
    return try {
        if (tipoServicio != "Velorio" && tipoServicio != "Entierro") {
            throw ServicioNoDisponibleException("Servicio '$tipoServicio' no disponible")
        }
        "Servicio $tipoServicio programado correctamente"
    } catch (e: ServicioNoDisponibleException) {
        "Error: ${e.message}"
    }
}

fun main() {
    println(realizarServicio("Cremación"))
    println(realizarServicio("Velorio"))
}
