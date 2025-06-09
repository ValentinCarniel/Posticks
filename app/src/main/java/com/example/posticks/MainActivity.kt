package com.example.posticks

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotasAdapter.OnNotaClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotasAdapter
    private lateinit var btnNuevaNota: Button
    private lateinit var etBuscar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerNotas)
        btnNuevaNota = findViewById(R.id.btnNuevaNota)
        etBuscar = findViewById(R.id.etBuscar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NotasAdapter(NotasData.obtenerNotas().toMutableList(), this)
        recyclerView.adapter = adapter

        btnNuevaNota.setOnClickListener {
            val intent = Intent(this, CrearEditarNotaActivity::class.java)
            intent.putExtra("isEdit", false)
            startActivity(intent)
        }

        // 🔍 Escucha de búsqueda
        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(texto: CharSequence?, start: Int, before: Int, count: Int) {
                val textoFiltrado = texto.toString()
                val notasFiltradas = NotasData.obtenerNotas().filter {
                    it.titulo.contains(textoFiltrado, ignoreCase = true) ||
                            it.contenido.contains(textoFiltrado, ignoreCase = true)
                }
                adapter.actualizarDatos(notasFiltradas)
            }
        })
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
