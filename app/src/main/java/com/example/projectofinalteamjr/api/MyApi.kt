package com.example.projectofinalteamjr.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyApi {
    @GET("cursos")
    fun getCursos(): Call<List<Cursos>>

    @Headers("Content-Type: application/json")
    @POST("cursos/create")
    fun criarCurso(
        @Body cursoNovo: Curso
    ): Call<Curso>

}

