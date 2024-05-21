package com.example.projectofinalteamjr.api

import java.io.Serializable

data class Modulos (
    val ModuloID: Int,
    val Nome: String,
    val Descricao: String,
    val Horas: Int,
    val Regime_modulo: String
):Serializable

// construtor para criação de novo modulo em DB
data class Modulo(val Nome: String, val Descricao: String, val Horas: Int, val Regime_modulo: String)