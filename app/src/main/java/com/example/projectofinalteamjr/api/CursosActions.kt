package com.example.projectofinalteamjr.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.projectofinalteamjr.cursos.EditarCursoActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// URL base para a API e tags para logs.
private val BASE_URL = "http://10.0.2.2:8000/api/"
private val TAG: String = "CHECK_RESPONSE"
private val TAG2: String = "Metodo Post"
// Lista de cursos para armazenar os cursos recebidos da API.
public var cursosList: ArrayList<Cursos>? = ArrayList()

class CursosActions {

    // Método para enviar uma solicitação para criar um novo curso na API.
    public fun sendRequestCursos(context: Context, curso: Curso) { // O sendRequestCursos envia uma solicitação para criar um novo curso na API
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
                    Toast.makeText(context, "Curso gravado com sucesso", Toast.LENGTH_SHORT).show()
//                    val cursoCriado = response.body()
//                    Log.i(TAG2, "onResponse: Curso criado ${cursoCriado?.Nome}")
                } else {
                    Toast.makeText(context, "Erro na gravaçao", Toast.LENGTH_SHORT).show()
//                    Log.i(TAG2, "onResponse: Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Curso>, t: Throwable) {
                // Handle failure
               Toast.makeText(context, "Erro no servidor", Toast.LENGTH_SHORT).show()
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
    public fun atualizarCurso(id: Int, curso: Curso) : String {
        val client = OkHttpClient.Builder().build()
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MyApi::class.java)
        val call = builder.atualizarCurso(id, curso)

        var resposta = ""

        call.enqueue(object : Callback<Curso> {
            override fun onResponse(call: Call<Curso>, response: Response<Curso>) {
                if (response.isSuccessful) {

                    resposta = "Curso atualizado com sucesso!"

                //val cursoAtualizado = response.body()

                //Log.i(TAG2, "onResponse: Curso atualizado ${cursoAtualizado?.Nome}")
                } else {

                    resposta = "Erro. Não foi possível atualizar o curso."

                //Log.i(TAG2, "onResponse: Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Curso>, t: Throwable) {
                resposta = "Erro. Falha na Base de Dados."
            }
        })
        return resposta
    }



}

