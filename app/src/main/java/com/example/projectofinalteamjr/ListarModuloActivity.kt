package com.example.projectofinalteamjr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.databinding.ActivityAdicionarModuloBinding

class ListarModuloActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarModuloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityAdicionarModuloBinding.inflate(layoutInflater)


    }
}