package com.example.posticks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotasAdapter(
    private var notas: List<Nota>,
    private val listener: OnNotaClickListener
) : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>() {

    interface OnNotaClickListener {
        fun onNotaClick(nota: Nota)
    }

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvContenido: TextView = itemView.findViewById(R.id.tvNota)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val nota = notas[position]
                    listener.onNotaClick(nota)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.tvTitulo.text = nota.titulo
        holder.tvContenido.text = nota.contenido
    }

    override fun getItemCount(): Int = notas.size

    fun actualizarDatos(nuevasNotas: List<Nota>) {
        notas = nuevasNotas
        notifyDataSetChanged()
    }
}
