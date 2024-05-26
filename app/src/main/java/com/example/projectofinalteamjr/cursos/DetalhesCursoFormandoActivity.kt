package com.example.projectofinalteamjr.cursos

import android.R
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.databinding.ActivityCursosFormandoBinding
import com.example.projectofinalteamjr.databinding.ActivityDetalhesCursoFormandoBinding

class DetalhesCursoFormandoActivity : AppCompatActivity() {
    private val binding by lazy{
    ActivityDetalhesCursoFormandoBinding.inflate(layoutInflater)
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        val curso = i.getSerializableExtra("curso") as Cursos
        val listaModulos = i.getSerializableExtra("modulos") as List<Modulos>
        val listaNomeModulos = ArrayList<String>()

        binding.nomeCursoFormandoId.text=curso.Nome
        binding.descricaoCursoFormandoId.text=curso.Descricao
        binding.totalhorasCursoFormandoId.text=curso.TotalHoras.toString()

        for (modulo in listaModulos){
            listaNomeModulos.add(modulo.Nome)
        }
        val arrayAdapterModulosCursosFormando = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1,
            listaNomeModulos
        ){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }
        binding.listaModulosCursoFormandoId.adapter=arrayAdapterModulosCursosFormando

        binding.buttonDetalhesCursoFormandoBack.setOnClickListener {
            finish()
        }



    }
}