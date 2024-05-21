package com.example.projectofinalteamjr.api

import java.io.Serializable

data class Cursos (
    val cursoID: Int,
    val Nome: String,
    val Descricao: String,
    val TotalHoras: Int
):Serializable

// Construtor

data class Curso(val Nome: String, val Descricao: String, val TotalHoras: Int)