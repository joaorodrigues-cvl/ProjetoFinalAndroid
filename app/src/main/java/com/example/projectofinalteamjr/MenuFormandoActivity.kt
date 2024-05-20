package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.databinding.ActivityMenuFormandoBinding


class MenuFormandoActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMenuFormandoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


            binding.buttonLogout.setOnClickListener {
                val iLogout: Intent = Intent(this@MenuFormandoActivity, MainActivity::class.java)
                startActivity(iLogout)
        }
    }
}