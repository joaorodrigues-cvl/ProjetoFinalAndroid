package com.example.projectofinalteamjr.faltasAdministrador

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.MenuAdminActivity
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.databinding.ActivityMenuFaltasAdminBinding

class MenuFaltasAdminActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMenuFaltasAdminBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@MenuFaltasAdminActivity, FaltasAdminActivity::class.java)
            startActivity(iBack)
        }

        binding.btnLancarFaltas.setOnClickListener{
                val iBack: Intent = Intent(this@MenuFaltasAdminActivity, FaltasAdminFormandoListActivity::class.java)
                startActivity(iBack)
        }

        binding.btnViewFaltas.setOnClickListener{
            val iBack: Intent = Intent(this@MenuFaltasAdminActivity, FaltasAdminVisualizarFormandoListActivity::class.java)
            startActivity(iBack)
        }

    }
}