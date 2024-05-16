package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.CursosActions
import com.example.projectofinalteamjr.databinding.ActivityAdicionarCursoBinding

class AdicionarCursoActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAdicionarCursoBinding
    private val cursosActions = CursosActions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicionarCursoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGravarCurso.setOnClickListener {
            val nome = binding.editNomeCurso.text.toString()
            val descricao = binding.editDescricaoCurso.text.toString()
            val totalHoras = binding.editHorasCurso.text.toString().toIntOrNull()?: 0


            if (nome.isNotEmpty() && descricao.isNotEmpty() && totalHoras > 0 ) {
                val curso = Curso(nome, descricao, totalHoras)
                cursosActions.sendRequestCursos(curso)
                startActivity(Intent(this, MenuAdminActivity::class.java))
            } else {
                // Show error message to user
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }


    }
}