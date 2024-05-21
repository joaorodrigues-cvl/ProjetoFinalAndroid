package com.example.projectofinalteamjr

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.ModulosActions
import com.example.projectofinalteamjr.databinding.ActivityModulosBinding

class ModulosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityModulosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent


        var listaNomesModulos = i.getStringArrayListExtra("listaNomesModulos")
        var listaDescricaoModulos = i.getStringArrayListExtra("listaDescricaoModulos")
        var listaRegimeModulos = i.getStringArrayListExtra("listaRegimeModulos")
        var listaHorasModulos = i.getIntegerArrayListExtra("listaHorasModulos")!!

        val arrayAdapterNomesModulos = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaNomesModulos!!) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.setTextColor(Color.WHITE) // cor do texto branco
                return view
            }
        }

        //val arrayAdapterDescricaoCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaDescricaoCursos!!)
        //val arrayAdapterHorasCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaHorasCursos!!)

        binding.nomeModulosID.adapter = arrayAdapterNomesModulos
        //binding.decricaoCursosID.adapter = arrayAdapterDescricaoCursos
        //binding.horasCursosID.adapter = arrayAdapterHorasCursos

        binding.nomeModulosID.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as String?
            val descricao = listaDescricaoModulos!!.get(position)
            val regime = listaRegimeModulos!!.get(position)
            val Totalhoras = listaHorasModulos!!.get(position)
            val idmodulo = position+1
            val intent = Intent(this, DetalhesModuloActivity::class.java)
            intent.putExtra("nomeModulo",element)
            intent.putExtra("descricaoModulo",descricao)
            intent.putExtra("regimeModulo", regime)
            intent.putExtra("horasModulo",Totalhoras)
            intent.putExtra("idModulo",idmodulo)
            startActivity(intent)

        }

        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.btnAddModulo.setOnClickListener {
            val iBack: Intent = Intent(this@ModulosActivity,AdicionarModuloActivity::class.java)
            startActivity(iBack)
        }
    }


}