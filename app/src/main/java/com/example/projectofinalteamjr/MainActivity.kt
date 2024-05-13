package com.example.projectofinalteamjr



import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityMainBinding
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

        // BOTOES MAIN MENU
        binding.btnCursos.setOnClickListener() {
            startActivity(Intent(this, ListarCursosActivity::class.java))
        }



        getApiCursos()

      // sendRequestCursos(curso)

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
    }

}