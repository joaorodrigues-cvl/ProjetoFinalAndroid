package com.example.projectofinalteamjr.api

import java.io.Serializable
import java.sql.Date

data class Turmas  (
    val turmaID: Int,
    val cursoID: Int,
    val nome: String,
    val localizacao: String,
    val datainicio: String,
    val datafim: String,
    val regime: String
) : Serializable
