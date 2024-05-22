package com.example.projectofinalteamjr.faltasFormando

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Faltas
import com.example.projectofinalteamjr.databinding.ActivityDetalhesFaltaBinding

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