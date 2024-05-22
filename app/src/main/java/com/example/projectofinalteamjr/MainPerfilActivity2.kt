package com.example.projectofinalteamjr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.databinding.ActivityAdicionarCursoBinding
import com.example.projectofinalteamjr.databinding.ActivityMainPerfil2Binding

class MainPerfilActivity2 : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainPerfil2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}