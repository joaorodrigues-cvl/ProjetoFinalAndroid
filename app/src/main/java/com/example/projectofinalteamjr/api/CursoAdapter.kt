package com.example.projectofinalteamjr.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofinalteamjr.R

class CursoAdapter(private val cursos: List<Curso>) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    // Classe interna para o ViewHolder
    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tv_nome)
        val tvDescricao: TextView = itemView.findViewById(R.id.tv_descricao)
        val tvTotalHoras: TextView = itemView.findViewById(R.id.tv_total_horas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        // Inflar o layout do item de curso
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_curso, parent, false)
        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        // Vincular os dados do curso ao ViewHolder
        val curso = cursos[position]
        holder.tvNome.text = curso.nome
        holder.tvDescricao.text = curso.descricao
        holder.tvTotalHoras.text = curso.totalHoras.toString()
    }

    override fun getItemCount(): Int {
        // Retornar o tamanho da lista de cursos
        return cursos.size
    }
}