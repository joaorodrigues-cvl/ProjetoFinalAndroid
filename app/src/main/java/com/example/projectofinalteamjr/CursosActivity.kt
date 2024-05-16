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
        var listaHorasCursos = i.getIntegerArrayListExtra("listaHorasCursos")

        val arrayAdapterNomesCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaNomesCursos!!)
        //val arrayAdapterDescricaoCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaDescricaoCursos!!)
        //val arrayAdapterHorasCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaHorasCursos!!)

        binding.nomeCursosID.adapter = arrayAdapterNomesCursos
        //binding.decricaoCursosID.adapter = arrayAdapterDescricaoCursos
        //binding.horasCursosID.adapter = arrayAdapterHorasCursos

        binding.nomeCursosID.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as String
            val intent = Intent(this, DetalhesCursoActivity::class.java)
            intent.putExtra("curso", element)
            startActivity(intent)
        }

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@CursosActivity,MenuAdminActivity::class.java)
            startActivity(iBack)
        }

    }


}