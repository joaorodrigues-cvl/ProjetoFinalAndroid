package com.example.projectofinalteamjr.faltasAdministrador

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.databinding.ActivityFaltasAdminVisualizarFaltasBinding

class FaltasAdminVisualizarFaltasActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFaltasAdminVisualizarFaltasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@FaltasAdminVisualizarFaltasActivity, FaltasAdminFormandoFaltasActivity::class.java)
            startActivity(iBack)
        }

    }
}