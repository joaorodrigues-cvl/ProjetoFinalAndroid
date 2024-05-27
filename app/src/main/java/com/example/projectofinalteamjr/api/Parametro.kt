package com.example.projectofinalteamjr.api

import java.io.Serializable

data class Parametro(

    val parametroID :Int,
    val nome: String,
    val descricao: String,
    val ponderacao: Double

):Serializable
