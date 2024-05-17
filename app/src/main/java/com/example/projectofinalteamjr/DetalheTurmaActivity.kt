package com.example.projectofinalteamjr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Turmas
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding
import com.example.projectofinalteamjr.databinding.ActivityDetalheTurmaBinding

class DetalheTurmaActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityDetalheTurmaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        val listaTurmas = i.getSerializableExtra("listaTurmas") as List<Turmas>

        binding.editNomeTurma.text=listaTurmas[0].nome

        binding.editRegimeTurma.text=listaTurmas[0].regime

        binding.editLocalizacaoTurma.text=listaTurmas[0].localizacao

        binding.editDataInicio.text=listaTurmas[0].datainicio

        binding.editDataFim.text=listaTurmas[0].datafim


    }
}