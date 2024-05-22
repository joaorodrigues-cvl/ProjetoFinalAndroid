package com.example.projectofinalteamjr.faltasFormando

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Faltas
import com.example.projectofinalteamjr.databinding.ActivityMainFaltasBinding
import java.io.Serializable


class MainFaltasActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainFaltasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        val listaFaltas = i.getSerializableExtra("listaFaltas") as List<Faltas>

        val listaDataFaltas = ArrayList<String>()

        for (falta in listaFaltas){
            listaDataFaltas.add(falta.data + " - " + falta.tipo)
        }

        val arrayAdapterDataFaltas = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaDataFaltas!!){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

        binding.dataFaltaID.adapter = arrayAdapterDataFaltas

        binding.dataFaltaID.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as String?
            val intent = Intent(this, DetalhesFaltaActivity::class.java)
            intent.putExtra("nomeModulo",element)
            intent.putExtra("falta",listaFaltas[position] as Serializable)
            startActivity(intent)

        }


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