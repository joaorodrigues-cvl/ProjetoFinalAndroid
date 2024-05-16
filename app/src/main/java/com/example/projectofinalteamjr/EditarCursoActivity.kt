package com.example.projectofinalteamjr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding
import com.example.projectofinalteamjr.databinding.ActivityEditarCursoBinding

class EditarCursoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarCursoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityEditarCursoBinding.inflate(layoutInflater)

        val cursoNome = intent.getStringExtra("cursoNome")
        val cursoDescricao = intent.getStringExtra("cursoDescricao")
        val cursoHoras = intent.getIntExtra("cursoHoras", 0)

        if (cursoNome != null) {
            binding.editNomeCurso.setText(cursoNome)
            binding.editDescricaoCurso.setText(cursoDescricao ?: "")
            binding.editHorasCurso.setText(cursoHoras.toString())
        }

        binding.btnEditarUrso.setOnClickListener {
            // Handle the save button click here
        }
    }
}