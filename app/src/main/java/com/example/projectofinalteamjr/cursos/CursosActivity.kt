package com.example.projectofinalteamjr.cursos

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.MenuAdminActivity
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding
import java.io.Serializable

class CursosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCursosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        val listaCursos = i.getSerializableExtra("listaCursos") as List<Cursos>

        val listaNomesCursos = ArrayList<String>()

        for(curso in listaCursos){
            listaNomesCursos.add(curso.Nome)
        }

        val arrayAdapterNomesCursos = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1,
            listaNomesCursos
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(R.id.text1)
                textView.setTextColor(Color.WHITE) // cor do texto branco
                return view
            }
        }

        binding.nomeCursosID.adapter = arrayAdapterNomesCursos

        binding.nomeCursosID.setOnItemClickListener { parent, view, position, id ->
            val cursoPosition = position
            val intent = Intent(this, DetalhesCursoActivity::class.java)
            intent.putExtra("cursoPosition", cursoPosition)
            intent.putExtra("listaCursos", listaCursos as Serializable)
            startActivity(intent)

        }

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this,MenuAdminActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddCurso.setOnClickListener {
            val iAddCurso: Intent = Intent(this@CursosActivity, AdicionarCursoActivity::class.java)
            startActivity(iAddCurso)
        }
    }


}