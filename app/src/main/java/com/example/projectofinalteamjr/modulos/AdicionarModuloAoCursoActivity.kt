package com.example.projectofinalteamjr.modulos

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.databinding.ActivityAdicionarModuloAoCursoBinding

class AdicionarModuloAoCursoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarModuloAoCursoBinding.inflate(layoutInflater)
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
        //binding.LVAddModulosToCurso.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        //binding.LVAddModulosToCurso.adapter = arrayAdapterNomesModulos



        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}