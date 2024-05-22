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
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding

class CursosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCursosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent


        var listaNomesCursos = i.getStringArrayListExtra("listaNomesCursos")
        var listaDescricaoCursos = i.getStringArrayListExtra("listaDescricaoCursos")
        var listaHorasCursos = i.getIntegerArrayListExtra("listaHorasCursos")!!

        val arrayAdapterNomesCursos = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaNomesCursos!!) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.setTextColor(Color.WHITE) // cor do texto branco
                return view
            }
        }

        //val arrayAdapterDescricaoCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaDescricaoCursos!!)
        //val arrayAdapterHorasCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaHorasCursos!!)

        binding.nomeCursosID.adapter = arrayAdapterNomesCursos
        //binding.decricaoCursosID.adapter = arrayAdapterDescricaoCursos
        //binding.horasCursosID.adapter = arrayAdapterHorasCursos

        binding.nomeCursosID.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as String?
            val descricao = listaDescricaoCursos!!.get(position)
            val Totalhoras = listaHorasCursos!!.get(position)
            val id = position+1
            val intent = Intent(this, DetalhesCursoActivity::class.java)
            intent.putExtra("nomeCurso",element)
            intent.putExtra("descricaoCurso",descricao)
            intent.putExtra("horasCurso",Totalhoras)
            intent.putExtra("idCurso",id)
            startActivity(intent)

        }

        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.btnAddCurso.setOnClickListener {
            val iBack: Intent = Intent(this@CursosActivity, AdicionarCursoActivity::class.java)
            startActivity(iBack)
        }
    }


}