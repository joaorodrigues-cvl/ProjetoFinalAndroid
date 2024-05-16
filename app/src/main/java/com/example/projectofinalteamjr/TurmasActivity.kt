package com.example.projectofinalteamjr

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Turmas
import com.example.projectofinalteamjr.databinding.ActivityMenuAdminBinding
import com.example.projectofinalteamjr.databinding.ActivityTurmasBinding

class TurmasActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityTurmasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        val listaTurmas = i.getSerializableExtra("listaTurmas") as List<Turmas>

        val listaNomesTurmas = ArrayList<String>()

        for (turma in listaTurmas){
            listaNomesTurmas.add(turma.nome)
        }


        val arrayAdapterTurmas = ArrayAdapter(this, R.layout.simple_list_item_1, listaNomesTurmas)

        binding.nomeTurmasID.adapter=arrayAdapterTurmas

    }
}