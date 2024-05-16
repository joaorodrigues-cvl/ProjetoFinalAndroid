package com.example.projectofinalteamjr

import android.R
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.cursosList
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding
import com.example.projectofinalteamjr.databinding.ActivityMainBinding
import java.util.ArrayList

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

        val arrayAdapterNomesCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaNomesCursos!!)
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
            val intent = Intent(this, EditarCursoActivity::class.java)
            intent.putExtra("nomeCurso",element)
            intent.putExtra("descricaoCurso",descricao)
            intent.putExtra("horasCurso",Totalhoras)
            intent.putExtra("idCurso",id)
            startActivity(intent)
        }

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@CursosActivity,MenuAdminActivity::class.java)
            startActivity(iBack)
        }

        binding.btnAddCurso.setOnClickListener {
            val iBack: Intent = Intent(this@CursosActivity,AdicionarCursoActivity::class.java)
            startActivity(iBack)
        }
    }


}