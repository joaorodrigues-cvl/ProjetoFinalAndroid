package com.example.projectofinalteamjr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.Curso


// Esta classe é um adaptador para o RecyclerView. Ele conecta os dados (lista de cursos) com a exibição na interface do usuário.

class CursosListAdapter (val listaCursos: ArrayList<Curso>):
    RecyclerView.Adapter<CursosListAdapter.CursoViewHolder>() {

    // ViewHolder representa uma única entrada na lista de cursos.
    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // O TextView que exibirá o nome do curso.
        val textViewCurso: TextView = itemView.findViewById(R.id.text_modelo) //text_modelo é o id do textview que vai conter o nome do curso


    }
//One Create à baixo
// Este método é chamado quando o RecyclerView precisa criar uma nova ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
    // Inflate the layout for each item in the RecyclerView.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_cursos_list,parent,false)

        return CursoViewHolder(view)
    }

    // Este método retorna o número total de itens na lista de cursos.
    override fun getItemCount(): Int {
        return listaCursos.size
    }

    // Este método é chamado pelo RecyclerView para exibir os dados na posição especificada.
    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = listaCursos[position]  // Obtém o curso na posição 'position'
        holder.textViewCurso.setText(curso.Nome) // Define o texto do TextView para o nome do curso.
    }
}