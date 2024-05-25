package com.example.projectofinalteamjr.api

import java.io.Serializable

data class avaliacaoModulo(
    val moduloID : Int,
    val formandoTurmaID : Int,
    val parametroID : Int,
    val notaParametro : Double
):Serializable
