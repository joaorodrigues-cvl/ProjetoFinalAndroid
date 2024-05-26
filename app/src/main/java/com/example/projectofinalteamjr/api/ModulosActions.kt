package com.example.projectofinalteamjr.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



private val BASE_URL = "http://10.0.2.2:8000/api/"
private val TAG: String = "Metodo Get Modulo"
private val TAG2: String = "Metodo Post Modulo"

var modulosList: ArrayList<Modulos>? = ArrayList()

class ModulosActions {

    fun sendRequestModulos(context: Context, modulo: Modulo) {
        val client = OkHttpClient.Builder().build()   // adicionado para funcioanr..
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MyApi::class.java)


        val call = builder.criarModulo(modulo) // cria um novo modulo do tipo modulo de acordo com os dados anteriores

        call.enqueue(object : Callback<Modulo> {
            override fun onResponse(call: Call<Modulo>, response: Response<Modulo>) {
                // Handle successful response
                if (response.isSuccessful) {
                    val moduloCriado = response.body()
                    Toast.makeText(context, "Módulo criado com sucesso", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(context, "Erro na gravação", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<Modulo>, t: Throwable) {
                // Handle failure
                Toast.makeText(context, "Erro no servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun getApiModulos() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getModulos().enqueue(object : Callback<List<Modulos>> {
            override fun onResponse(
                call: Call<List<Modulos>>,
                response: Response<List<Modulos>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (modulo in it) {
                            Log.i(TAG, "onResponse: ${modulo.Nome}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Modulos>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })
    }
    fun atualizarModulo(id: Int, modulo: Modulo) {
        val client = OkHttpClient.Builder().build()
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MyApi::class.java)
        val call = builder.atualizarModulo(id, modulo)

        call.enqueue(object : Callback<Modulo> {
            override fun onResponse(call: Call<Modulo>, response: Response<Modulo>) {
                if (response.isSuccessful) {
                    val moduloAtualizado = response.body()

                    Log.i(TAG2, "onResponse: Modulo atualizado ${moduloAtualizado?.Nome}")
                } else {
                    Log.i(TAG2, "onResponse: Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Modulo>, t: Throwable) {
                Log.i(TAG2, "onFailure: Network request failed: ${t.message}")
            }
        })
    }


}