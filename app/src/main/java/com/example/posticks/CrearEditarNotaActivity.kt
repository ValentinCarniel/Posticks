package com.example.posticks

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearEditarNotaActivity : AppCompatActivity() {

    private lateinit var etTitulo: EditText
    private lateinit var etContenido: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_editar_nota)

        etTitulo = findViewById(R.id.etTitulo)
        etContenido = findViewById(R.id.etContenido)
        btnGuardar = findViewById(R.id.btnGuardar)

        val isEdit = intent.getBooleanExtra("isEdit", false)
        val notaId = intent.getIntExtra("notaId", -1)

        if (isEdit) {
            val nota = NotasData.obtenerNotaPorId(notaId)
            nota?.let {
                etTitulo.setText(it.titulo)
                etContenido.setText(it.contenido)
            }
            btnGuardar.text = "Actualizar"
        } else {
            btnGuardar.text = "Guardar"
        }

        btnGuardar.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            val contenido = etContenido.text.toString().trim()

            if (titulo.isEmpty() || contenido.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                if (isEdit) {
                    NotasData.actualizarNota(notaId, titulo, contenido)
                    Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show()
                } else {
                    NotasData.agregarNota(titulo, contenido)
                    Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }
    }
}
