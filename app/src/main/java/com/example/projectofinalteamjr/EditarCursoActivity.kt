package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.CursosActions
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding
import com.example.projectofinalteamjr.databinding.ActivityDetalhesCursoBinding
import com.example.projectofinalteamjr.databinding.ActivityEditarCursoBinding

class EditarCursoActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityEditarCursoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cursosActions = CursosActions()
        setContentView(binding.root)

        val intent = intent

        val nomeCurso = intent.getStringExtra("nomeCurso")
        val descricaoCurso = intent.getStringExtra("descricaoCurso")
        val horasCurso = intent.getIntExtra("horasCurso", 0)
        val idCurso = intent.getIntExtra("idCurso",0)

        binding.editNomeCurso.setText(nomeCurso)
        binding.editDescricaoCurso.setText(descricaoCurso)
        binding.editHorasCurso.setText(horasCurso.toString())

        binding.btnGravarEditCurso.setOnClickListener {
            val nome = binding.editNomeCurso.text.toString()
            val descricao = binding.editDescricaoCurso.text.toString()
            val totalHoras = binding.editHorasCurso.text.toString().toIntOrNull()?: 0


            if (nome.isNotEmpty() && descricao.isNotEmpty() && totalHoras > 0 ) {
                val curso = Curso(nome, descricao, totalHoras)
                cursosActions.atualizarCurso(idCurso, curso)

                startActivity(Intent(this, MenuAdminActivity::class.java))
            } else {
                // Show error message to user
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@EditarCursoActivity,CursosActivity::class.java)
            startActivity(iBack)
        }

    }
}