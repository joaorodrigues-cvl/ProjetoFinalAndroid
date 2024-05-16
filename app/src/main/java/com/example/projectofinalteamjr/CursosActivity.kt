package com.example.projectofinalteamjr


import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.cursosList
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
        var listaHorasCursos = i.getIntegerArrayListExtra("listaHorasCursos")

        val arrayAdapterNomesCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaNomesCursos!!)
        //val arrayAdapterDescricaoCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaDescricaoCursos!!)
        //val arrayAdapterHorasCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaHorasCursos!!)

        binding.nomeCursosID.adapter = arrayAdapterNomesCursos
        //binding.decricaoCursosID.adapter = arrayAdapterDescricaoCursos
        //binding.horasCursosID.adapter = arrayAdapterHorasCursos

        binding.nomeCursosID.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // Obtenha o objeto Curso correspondente ao item selecionado
            val cursoSelecionado = cursosList?.get(position)

            // Inicie a EditarCursoActivity e passe o objeto Curso como extra
            val intent = Intent(this, EditarCursoActivity::class.java)
            intent.putExtra("cursoSelecionado", cursoSelecionado)
            startActivity(intent)
        }

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@CursosActivity,MenuAdminActivity::class.java)
            startActivity(iBack)
        }

    }


}