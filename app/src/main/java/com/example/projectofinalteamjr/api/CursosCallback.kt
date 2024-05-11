package com.example.projectofinalteamjr.api

interface CursosCallback {
    fun onCursosLoaded(cursosList: List<Cursos>)
    fun onError(errorMessage: String)
}