package com.example.posticks

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvContent: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button
    private lateinit var btnBack: Button

    private var currentNota: Nota? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTitle = findViewById(R.id.tvTitle)
        tvDate = findViewById(R.id.tvDate)
        tvContent = findViewById(R.id.tvContent)
        btnEdit = findViewById(R.id.btnEdit)
        btnDelete = findViewById(R.id.btnDelete)
        btnBack = findViewById(R.id.btnBack)

        val notaId = intent.getIntExtra("notaId", -1)
        if (notaId == -1) {
            Toast.makeText(this, "Nota no encontrada", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        currentNota = NotasData.obtenerNotas().find { it.id == notaId }
        if (currentNota == null) {
            Toast.makeText(this, "Nota no encontrada", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        tvTitle.text = currentNota!!.titulo
        tvDate.text = ""  // Aquí quité la fecha porque no tienes creationDate
        tvContent.text = currentNota!!.contenido

        btnEdit.setOnClickListener {
            val intent = Intent(this, CrearEditarNotaActivity::class.java)
            intent.putExtra("isEdit", true)
            intent.putExtra("notaId", currentNota!!.id)
            startActivity(intent)
            finish()
        }

        btnDelete.setOnClickListener {
            val eliminado = NotasData.eliminarNota(currentNota!!.id)
            if (eliminado) {
                Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al eliminar la nota", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
