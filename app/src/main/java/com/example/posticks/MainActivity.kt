package com.example.posticks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posticks.adapter.NotaAdapter
import com.example.posticks.databinding.ActivityMainBinding
import com.example.posticks.model.Nota
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notaAdapter: NotaAdapter
    private val listaNotas = mutableListOf(
        Nota("Comprar leche", "20:30"),
        Nota("Estudiar Kotlin", "21:00"),
        Nota("Visitar ChatGPT", "22:15")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notaAdapter = NotaAdapter(listaNotas)
        binding.rvNotas.layoutManager = LinearLayoutManager(this)
        binding.rvNotas.adapter = notaAdapter

        binding.btnAgregar.setOnClickListener {
            val horaActual = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            listaNotas.add(Nota("Nueva nota", horaActual))
            notaAdapter.notifyItemInserted(listaNotas.size - 1)
        }
    }
}