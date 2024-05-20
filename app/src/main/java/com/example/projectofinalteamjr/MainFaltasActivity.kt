package com.example.projectofinalteamjr

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.databinding.ActivityMainFaltasBinding
import com.example.projectofinalteamjr.databinding.ActivityModulosBinding


class MainFaltasActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainFaltasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val listaDataFaltas = ArrayList<String>()

        listaDataFaltas.add("19/03/2024")
        listaDataFaltas.add("21/03/2024")
        listaDataFaltas.add("22/03/2024")

        val arrayAdapterDataFaltas = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaDataFaltas!!){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

        binding.dataFaltaID.adapter = arrayAdapterDataFaltas

        val listaTipoFalta = ArrayList<String>()

        listaTipoFalta.add("Justificada")
        listaTipoFalta.add("Injustificada")
        listaTipoFalta.add("Justificada")

        val arrayAdapterTipoFaltas = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaTipoFalta!!){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

        binding.tipoFaltaID.adapter = arrayAdapterTipoFaltas

        binding.buttonFaltasBack.setOnClickListener {
            finish()
        }

       /* val listView = findViewById<ListView>(R.id.nomeCursoID)
        val items = ArrayList<String>()
        items.add("Data 1")
        items.add("Data 2")
        items.add("Data 3")

        val adapter = MyListAdapter(this, items)
        listView.adapter = adapter*/
    }
}