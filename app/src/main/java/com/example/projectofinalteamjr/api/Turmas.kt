package com.example.projectofinalteamjr.api

import java.sql.Date

data class Turmas (
    val turmaID: Int,
    val cursoID: Int,
    val nome: String,
    val localizacao: String,
    val datainicio: Date,
    val datafim: Date,
    val Regime: String
)
