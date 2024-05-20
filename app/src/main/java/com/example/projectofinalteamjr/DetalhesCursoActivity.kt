package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding
import com.example.projectofinalteamjr.databinding.ActivityDetalhesCursoBinding

class DetalhesCursoActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityDetalhesCursoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val intent = intent

        val nomeCurso = intent.getStringExtra("nomeCurso")
        val descricaoCurso = intent.getStringExtra("descricaoCurso")
        val horasCurso = intent.getIntExtra("horasCurso", 0)
        binding.editNomeCurso.text = nomeCurso
        binding.editDescricaoCurso.text = descricaoCurso
        binding.editHorasCurso.text = horasCurso.toString()

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@DetalhesCursoActivity,CursosActivity::class.java)
            startActivity(iBack)
        }
        binding.btnEditCurso.setOnClickListener {
            val nomeCurso = intent.getStringExtra("nomeCurso")
            val descricaoCurso = intent.getStringExtra("descricaoCurso")
            val horasCurso = intent.getIntExtra("horasCurso", 0)
            val idCurso = intent.getIntExtra("idCurso", -1)

            val intentEditarCurso = Intent(this, EditarCursoActivity::class.java)
            intentEditarCurso.putExtra("nomeCurso", nomeCurso)
            intentEditarCurso.putExtra("descricaoCurso", descricaoCurso)
            intentEditarCurso.putExtra("horasCurso", horasCurso)
            intentEditarCurso.putExtra("idCurso", idCurso)
            startActivity(intentEditarCurso)
        }
    }
}