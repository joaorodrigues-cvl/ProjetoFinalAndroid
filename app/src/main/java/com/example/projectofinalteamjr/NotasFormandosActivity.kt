package com.example.projectofinalteamjr

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.ModulosNotas
import com.example.projectofinalteamjr.databinding.ActivityMainFaltasBinding
import com.example.projectofinalteamjr.databinding.ActivityNotasFormandosBinding

class NotasFormandosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityNotasFormandosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val i = intent

        val listaNotas = i.getSerializableExtra("notas") as ModulosNotas

        val notasFormando = listaNotas.results
        val modulos = listaNotas.moduloNomes

        val arrayAdapterNotas = object : ArrayAdapter<Double>(this, android.R.layout.simple_list_item_1, notasFormando!!){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

        val arrayAdapterModulos = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modulos!!){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

        binding.NotaFormandoId.adapter = arrayAdapterNotas
        binding.modulosNotasId.adapter = arrayAdapterModulos

        binding.buttonBackNotasFormando.setOnClickListener {
            finish()
        }


    }
}