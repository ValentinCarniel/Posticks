package com.example.posticks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotasAdapter.OnNotaClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotasAdapter
    private lateinit var btnNuevaNota: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerNotas)
        btnNuevaNota = findViewById(R.id.btnNuevaNota)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NotasAdapter(NotasData.obtenerNotas().toMutableList(), this)
        recyclerView.adapter = adapter

        btnNuevaNota.setOnClickListener {
            val intent = Intent(this, CrearEditarNotaActivity::class.java)
            intent.putExtra("isEdit", false)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.actualizarDatos(NotasData.obtenerNotas())
    }

    override fun onNotaClick(nota: Nota) {
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("notaId", nota.id)
        startActivity(intent)
    }
}
