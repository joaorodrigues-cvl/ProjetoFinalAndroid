package com.example.projectofinalteamjr.modulos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.databinding.ActivityDetalhesModuloBinding

class DetalhesModuloActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityDetalhesModuloBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val intent = intent

        val nomeModulo = intent.getStringExtra("nomeModulo")
        val descricaoModulo = intent.getStringExtra("descricaoModulo")
        val regimeModulo = intent.getStringExtra("regimeModulo")
        val horasModulo = intent.getIntExtra("horasModulo", 0)
        binding.editNomeModulo.text = nomeModulo
        binding.editDescricaoModulo.text = descricaoModulo
        binding.editRegimeModulo.text = regimeModulo
        binding.editHorasModulo.text = horasModulo.toString()

        binding.buttonBack.setOnClickListener {
            finish()
        }
        binding.btnEditModulo.setOnClickListener {
            val nomeModulo = intent.getStringExtra("nomeModulo")
            val descricaoModulo = intent.getStringExtra("descricaoModulo")
            val regimeModulo = intent.getStringExtra("regimeModulo")
            val horasModulo = intent.getIntExtra("horasModulo", 0)
            val idModulo = intent.getIntExtra("idModulo", -1)

            val intentEditarModulo = Intent(this, EditarModuloActivity::class.java)
            intentEditarModulo.putExtra("nomeModulo", nomeModulo)
            intentEditarModulo.putExtra("descricaoModulo", descricaoModulo)
            intentEditarModulo.putExtra("regimeModulo", regimeModulo)
            intentEditarModulo.putExtra("horasModulo", horasModulo)
            intentEditarModulo.putExtra("idModulo", idModulo)
            startActivity(intentEditarModulo)
        }
    }
}