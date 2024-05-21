package com.example.projectofinalteamjr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectofinalteamjr.adapter.CursosListAdapter
import com.example.projectofinalteamjr.data.CursosMock
import com.example.projectofinalteamjr.databinding.ActivityMainRecyclerView2Binding

class MainRecyclerViewActivity2 : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainRecyclerView2Binding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        }
    }
