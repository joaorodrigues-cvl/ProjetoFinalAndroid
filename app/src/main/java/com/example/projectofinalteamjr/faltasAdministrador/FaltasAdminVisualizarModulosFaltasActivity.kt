package com.example.projectofinalteamjr.faltasAdministrador

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.databinding.ActivityFaltasAdminVisualizarModulosFaltasBinding

class FaltasAdminVisualizarModulosFaltasActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFaltasAdminVisualizarModulosFaltasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@FaltasAdminVisualizarModulosFaltasActivity, FaltasAdminFormandoFaltasActivity::class.java)
            startActivity(iBack)
        }

    }
}