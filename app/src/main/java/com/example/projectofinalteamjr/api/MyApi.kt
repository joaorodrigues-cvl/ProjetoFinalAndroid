package com.example.projectofinalteamjr.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MyApi {
    @GET("cursos")
    fun getCursos(): Call<ArrayList<Cursos>>

    @Headers("Content-Type: application/json")
    @POST("cursos/create")
    fun criarCurso(
        @Body cursoNovo: Curso
    ): Call<Curso>

    @Headers("Content-Type: application/json")
    @PUT("curso_update/{id}")
    fun atualizarCurso(
        @Path("id") id: Int,
        @Body curso: Curso
    ): Call<Curso>

    @Headers("Content-Type: application/json")
    @PUT("modulo_update/{id}")
    fun atualizarModulo(
        @Path("id") id: Int,
        @Body modulo: Modulo
    ): Call<Modulo>

    @GET("modulos")
    fun getModulos(): Call<List<Modulos>>

    @Headers("Content-Type: application/json")
    @POST("modulos/create")
    fun criarModulo(
        @Body moduloNovo: Modulo
    ): Call<Modulo>

    @GET("turmas")
    fun getTurmas(): Call<List<Turmas>>

    @GET("turmas/{id}")
    fun detalhesTurma(
        @Path("id") id: Int
    ): Call<DetalhesTurma>

    @GET("faltas/{userID}")
    fun getFaltas(
        @Path("userID") id: Int
    ): Call<List<Faltas>>

    @GET("cursos/{userID}")
    fun getCursosFormando(
        @Path("userID") id: Int
    ): Call<List<Cursos>>

    @GET("modulos/{cursoID}")
    fun getModulosCurso(
        @Path("cursoID") id: Int
    ): Call<List<Modulos>>

    @GET ("user/{userID}")
    fun getUserInfo(
        @Path ("userID") id: Int
    ): Call<User>

    @GET ("notas/{userID}")
    fun getNotas(
        @Path ("userID") id: Int
    ): Call<ModulosNotas>

    @GET ("user/{userID}")
    fun getUserInfoNome(
        @Path ("userID") id: Int
    ): Call<List<User>>

    @POST ("turma/create")
    fun criarTurma(
        @Body turmaNova : Turma
    ): Call<Turma>

    @POST ("curso/{cursoID}/{moduloID}")
    fun adicionarModuloCurso(
        @Path("cursoID") cursoId: Int,
        @Path("moduloID") moduloId: Int
    ): Call<CursoModulo>

    @GET ("modulos_formando/{userID}")
    fun getModulosFormando(
        @Path("userID") id: Int
    ): Call<List<Modulos>>

    @GET ("parametros/index")
    fun getParametros(
    ): Call<List<Parametro>>

}

