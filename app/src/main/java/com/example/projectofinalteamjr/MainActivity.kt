package com.example.projectofinalteamjr

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.CursosActions
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "http://10.0.2.2:8000/api/"
    private val TAG: String = "CHECK_RESPONSE"
    private val TAG2: String = "Metodo Post"

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // Rotina de criação de um curso novo

        val curso = Curso(
            Nome = "Introduction to Programming Test3",
            Descricao = "Learn the basics of programming Test3",
            TotalHoras = 40
        )

        val cursoAction = CursosActions() // Cria um novo objeto do tipo cursoAction
        cursoAction.sendRequestCursos(curso) //chama a função desse novo objeto para enviar um novo curso para a DB

        // .............................................................................

        // Rotina para buscar os cursos

        cursoAction.getApiCursos() // chama a função desse novo objeto para buscar os cursos

    }
   /* private fun sendRequestCursos(curso: Curso) {
        val client = OkHttpClient.Builder().build()   // adicionado para funcionar..
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
                    val cursoCriado = response.body()
                    Log.i(TAG2, "onResponse: Curso criado ${cursoCriado?.Nome}")
                } else {
                    Log.i(TAG2, "onResponse: Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Curso>, t: Throwable) {
                // Handle failure
                Log.i(TAG2, "onFailure: Network request failed: ${t.message}")
            }
        })
    }


    private fun getApiCursos() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getCursos().enqueue(object : Callback<List<Cursos>> {
            override fun onResponse(
                call: Call<List<Cursos>>,
                response: Response<List<Cursos>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (curso in it) {
                            Log.i(TAG, "onResponse: ${curso.Nome}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Cursos>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }

        })
    }*/
}