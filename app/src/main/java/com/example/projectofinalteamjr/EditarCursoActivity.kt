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

        val cursosNomeList = intent.getSerializableExtra("listaNomesCursos") as ArrayList<String>
        val cursosDescricaoList = intent.getSerializableExtra("listaDescricaoCursos") as ArrayList<String>
        val cursosHorasList = intent.getSerializableExtra("listaHorasCursos") as ArrayList<Int>

        if (cursosNomeList.isNotEmpty()) {
            val cursoIndex = 0 // Set the index of the curso you want to display
            binding.editNomeCurso.setText(cursosNomeList[cursoIndex])
            binding.editDescricaoCurso.setText(cursosDescricaoList[cursoIndex])
            binding.editHorasCurso.setText(cursosHorasList[cursoIndex].toString())
        }

        binding.btnEditarUrso.setOnClickListener {
            // Handle the save button click here
        }
    }
}