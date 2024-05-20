package com.example.projectofinalteamjr

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainFaltasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_faltas)

        val listView = findViewById<ListView>(R.id.nomeCursoID)
        val items = ArrayList<String>()
        items.add("Data 1")
        items.add("Data 2")
        items.add("Data 3")

        val adapter = MyListAdapter(this, items)
        listView.adapter = adapter
    }
}