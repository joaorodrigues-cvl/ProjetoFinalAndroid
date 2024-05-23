package com.example.projectofinalteamjr

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.databinding.ActivityMainFaltasBinding
import com.example.projectofinalteamjr.databinding.ActivityNotasFormandosBinding

class NotasFormandosActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityNotasFormandosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)




    }
}