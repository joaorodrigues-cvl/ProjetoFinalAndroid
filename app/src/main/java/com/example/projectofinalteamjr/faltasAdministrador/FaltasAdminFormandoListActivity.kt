package com.example.projectofinalteamjr.faltasAdministrador

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.databinding.ActivityFaltasAdminTurmaFormandoBinding

class FaltasAdminFormandoListActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFaltasAdminTurmaFormandoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)



        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@FaltasAdminFormandoListActivity, FaltasAdminActivity::class.java)
            startActivity(iBack)
        }
    }
}