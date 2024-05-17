package com.example.projectofinalteamjr.api

import java.io.Serializable

data class DetalhesTurma(
    val turmaID: Int,
    val cursoID: Int,
    val nome: String,
    val localizacao: String,
    val datainicio: String,
    val datafim: String,
    val regime: String,
    val cursoNome: String,
    val formandoNome: List<String>
): Serializable
