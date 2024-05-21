package com.example.projectofinalteamjr.api

data class Modulos (
    val ModuloID: Int,
    val Nome: String,
    val Descricao: String,
    val Regime_modulo: String,
    val Horas: Int

)

// construtor para criação de novo modulo em DB
data class Modulo(val Nome: String, val Descricao: String, val Regime_modulo: String ,val Horas: Int)