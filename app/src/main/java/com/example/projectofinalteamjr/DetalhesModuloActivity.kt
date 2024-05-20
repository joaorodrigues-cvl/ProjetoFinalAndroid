package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.databinding.ActivityDetalhesCursoBinding
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
            val iBack: Intent = Intent(this@DetalhesModuloActivity,ModulosActivity::class.java)
            startActivity(iBack)
        }

    }
}