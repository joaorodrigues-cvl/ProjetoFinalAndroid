package com.example.projectofinalteamjr.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val BASE_URL = "http://10.0.2.2:8000/api/"
private val TAG: String = "CHECK_RESPONSE"
private val TAG2: String = "Metodo Post"

public var cursosList: ArrayList<Cursos>? = ArrayList()

class CursosActions {

    public fun sendRequestCursos(curso: Curso) {
        val client = OkHttpClient.Builder().build()   // adicionado para funcioanr..
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MyApi::class.java)
        val call = builder.criarCurso(curso) // cria um novo curso do tipo curso de acordo com os dados anteriores

        call.enqueue(object : Callback<Curso> {
            override fun onResponse(call: Call<Curso>, response: Response<Curso>) {
                // Handle successful response
                if (response.isSuccessful) {
                    return
//                    val cursoCriado = response.body()
//                    Log.i(TAG2, "onResponse: Curso criado ${cursoCriado?.Nome}")
                } else {
                    return
//                    Log.i(TAG2, "onResponse: Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Curso>, t: Throwable) {
                // Handle failure
                return
//                Log.i(TAG2, "onFailure: Network request failed: ${t.message}")
            }
        })
    }


    public fun getApiCursos() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val myApi=api.create(MyApi::class.java);

        myApi.getCursos().enqueue(object : Callback<ArrayList<Cursos>> {
            override fun onResponse(
                call: Call<ArrayList<Cursos>>,
                response: Response<ArrayList<Cursos>>
            ) {
                if (response.isSuccessful) {

                    var output = response.body() // Store the list
                    output?.let {
                        for (curso in it) {
                            cursosList!!.add(curso)

                            // Process each curso object
                            // You can access properties like curso.Nome here
                        }
                        Log.i("Lista","Curso name: ${
                            cursosList?.get(0)?.Nome
                        }")

                    }
                } else {
                    Log.i(TAG, "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<Cursos>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })
    }
    public fun atualizarCurso(id: Int, curso: Curso) {
        val client = OkHttpClient.Builder().build()
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MyApi::class.java)
        val call = builder.atualizarCurso(id, curso)

        call.enqueue(object : Callback<Curso> {
            override fun onResponse(call: Call<Curso>, response: Response<Curso>) {
                if (response.isSuccessful) {
                    val cursoAtualizado = response.body()
                    Log.i(TAG2, "onResponse: Curso atualizado ${cursoAtualizado?.Nome}")
                } else {
                    Log.i(TAG2, "onResponse: Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Curso>, t: Throwable) {
                Log.i(TAG2, "onFailure: Network request failed: ${t.message}")
            }
        })
    }



}

