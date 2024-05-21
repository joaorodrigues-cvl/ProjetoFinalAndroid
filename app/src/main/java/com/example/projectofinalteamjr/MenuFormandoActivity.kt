package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.Faltas
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityMenuFormandoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable


class MenuFormandoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMenuFormandoBinding.inflate(layoutInflater)
    }

    val BASE_URL = "http://10.0.2.2:8000/api/"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val myApi=api.create(MyApi::class.java);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        binding.buttonLogout.setOnClickListener {
            val iLogout: Intent = Intent(this@MenuFormandoActivity, MainActivity::class.java)
            startActivity(iLogout)
        }
        binding.getCursosFormando.setOnClickListener {

        }
        binding.getNotasFormando.setOnClickListener {

        }
        binding.getFaltasFormando.setOnClickListener {

            myApi.getFaltas(3).enqueue(object : Callback<List<Faltas>> {
                override fun onResponse(
                    call: Call<List<Faltas>>,
                    response: Response<List<Faltas>>
                ) {
                    if (response.isSuccessful) {

                        var faltas = response.body() // Store the list

                        // Intent:

                        val intent = Intent(this@MenuFormandoActivity, MainFaltasActivity::class.java)
                        intent.putExtra("listaFaltas", faltas as Serializable)
                        startActivity(intent)
                    } else {

                        // Fazer Toast

                        Toast.makeText(applicationContext, "Não foi possivel realizar a operação", Toast.LENGTH_LONG).show()

                        //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Faltas>>, t: Throwable) {

                    // Fazer Toast

                    Toast.makeText(applicationContext, "Falha ao tentar aceder o servidor", Toast.LENGTH_LONG).show()

                    //Log.i(TAG, "onFailure: ${t.message}")
                }
            })






        }


    }
}