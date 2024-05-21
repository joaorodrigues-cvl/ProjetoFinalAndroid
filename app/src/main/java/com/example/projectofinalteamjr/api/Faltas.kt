package com.example.projectofinalteamjr.api

import java.io.Serializable

data class Faltas(
    val data: String,
    val tempo: Int,
    val tipo: String,
    val nome: String
) : Serializable
