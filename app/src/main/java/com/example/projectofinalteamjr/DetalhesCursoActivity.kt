package com.example.projectofinalteamjr

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

        val i = intent

        val nomeCurso = intent.getStringExtra("curso")!!

        binding.textView3.text=nomeCurso



    }
}