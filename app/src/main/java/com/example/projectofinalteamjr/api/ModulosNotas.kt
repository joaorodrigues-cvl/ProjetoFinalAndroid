package com.example.projectofinalteamjr.api

import java.io.Serializable

data class ModulosNotas(
    val moduloNomes : List<String>,
    val results : List<Double>

):Serializable
