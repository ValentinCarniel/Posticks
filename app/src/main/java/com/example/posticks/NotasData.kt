package com.example.posticks

data class Nota(
    val id: Int,
    var titulo: String,
    var contenido: String
)

object NotasData {
    private val listaNotas = mutableListOf<Nota>()

    fun agregarNota(titulo: String, contenido: String) {
        val nuevaNota = Nota(
            id = if (listaNotas.isEmpty()) 1 else listaNotas.last().id + 1,
            titulo = titulo,
            contenido = contenido
        )
        listaNotas.add(nuevaNota)
    }

    fun obtenerNotas(): List<Nota> {
        return listaNotas
    }

    fun obtenerNotaPorId(id: Int): Nota? {
        return listaNotas.find { it.id == id }
    }

    fun actualizarNota(id: Int, nuevoTitulo: String, nuevoContenido: String) {
        val nota = obtenerNotaPorId(id)
        nota?.let {
            it.titulo = nuevoTitulo
            it.contenido = nuevoContenido
        }
    }

    fun eliminarNota(id: Int): Boolean {
        return listaNotas.removeIf { it.id == id }
    }

    fun limpiarNotas() {
        listaNotas.clear()
    }
}

