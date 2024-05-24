package com.example.projectofinalteamjr.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DetalhesTurma(
    val Turma: Turma,
    val CursoTurma: CursoTurma,
    val Formandos: ArrayList<Formandos>
):Serializable

data class Turma(
    val cursoID: Int,
    val nome: String,
    val localizacao: String,
    val datainicio: String,
    val datafim: String,
    val regime: String
):Serializable

data class CursoTurma(
    val cursoNome: String
):Serializable

data class Formandos(
    val formandoNome: String
):Serializable



