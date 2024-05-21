package com.example.projectofinalteamjr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Faltas
import com.example.projectofinalteamjr.databinding.ActivityDetalhesFaltaBinding
import com.example.projectofinalteamjr.databinding.ActivityMainFaltasBinding

class DetalhesFaltaActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityDetalhesFaltaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val i = intent

        val falta = i.getSerializableExtra("falta") as Faltas

        binding.editDataFalta.text = falta.data
        binding.editModuloFalta.text = falta.nome
        binding.editHorasFalta.text = falta.tempo.toString()
        binding.editTipoFalta.text = falta.tipo

        binding.buttonDetalhesFaltasBack.setOnClickListener {
            finish()
        }

    }
}