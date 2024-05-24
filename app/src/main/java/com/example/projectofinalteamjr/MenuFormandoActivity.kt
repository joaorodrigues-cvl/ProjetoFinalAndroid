package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.Faltas
import com.example.projectofinalteamjr.api.ModulosNotas
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.User
import com.example.projectofinalteamjr.databinding.ActivityMenuFormandoBinding
import com.example.projectofinalteamjr.faltasFormando.MainFaltasActivity
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

    val myApi = api.create(MyApi::class.java);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        binding.buttonLogout.setOnClickListener {
            val iLogout: Intent = Intent(this@MenuFormandoActivity, MainActivity::class.java)
            startActivity(iLogout)
        }
        binding.getCursosFormando.setOnClickListener {

            myApi.getCursosFormando(4).enqueue(object : Callback<List<Cursos>> {
                override fun onResponse(
                    call: Call<List<Cursos>>,
                    response: Response<List<Cursos>>
                ) {
                    if (response.isSuccessful) {

                        var cursos = response.body() // Store the list

                        // Intent:

                        val intent =
                            Intent(this@MenuFormandoActivity, CursosFormandoActivity::class.java)
                        intent.putExtra("listaCursos", cursos as Serializable)
                        startActivity(intent)
                    } else {

                        // Fazer Toast

                        Toast.makeText(
                            applicationContext,
                            "Não foi possivel realizar a operação",
                            Toast.LENGTH_LONG
                        ).show()

                        //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Cursos>>, t: Throwable) {

                    // Fazer Toast

                    Toast.makeText(
                        applicationContext,
                        "Falha ao tentar aceder o servidor",
                        Toast.LENGTH_LONG
                    ).show()

                    //Log.i(TAG, "onFailure: ${t.message}")
                }
            })
        }
        binding.getNotasFormando.setOnClickListener {

            myApi.getNotas(3).enqueue(object : Callback<ModulosNotas> {
                override fun onResponse(
                    call: Call<ModulosNotas>,
                    response: Response<ModulosNotas>
                ) {
                    if (response.isSuccessful) {

                        var notas = response.body() // Store the list

                        // Intent:

                        val intent =
                            Intent(this@MenuFormandoActivity, NotasFormandosActivity::class.java)
                        intent.putExtra("notas", notas as Serializable)
                        startActivity(intent)
                    } else {

                        // Fazer Toast

                        Toast.makeText(
                            applicationContext,
                            "Não foi possivel realizar a operação",
                            Toast.LENGTH_LONG
                        ).show()

                        //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ModulosNotas>, t: Throwable) {

                    // Fazer Toast

                    Toast.makeText(
                        applicationContext,
                        "Falha ao tentar aceder o servidor",
                        Toast.LENGTH_LONG
                    ).show()

                    //Log.i(TAG, "onFailure: ${t.message}")
                }
            })


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

                        val intent =
                            Intent(this@MenuFormandoActivity, MainFaltasActivity::class.java)
                        intent.putExtra("listaFaltas", faltas as Serializable)
                        startActivity(intent)
                    } else {

                        // Fazer Toast

                        Toast.makeText(
                            applicationContext,
                            "Não foi possivel realizar a operação",
                            Toast.LENGTH_LONG
                        ).show()

                        //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Faltas>>, t: Throwable) {

                    // Fazer Toast

                    Toast.makeText(
                        applicationContext,
                        "Falha ao tentar aceder o servidor",
                        Toast.LENGTH_LONG
                    ).show()

                    //Log.i(TAG, "onFailure: ${t.message}")
                }
            })


        }
        binding.getInfoFormando.setOnClickListener {

            myApi.getUserInfo(4).enqueue(object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {

                        var user = response.body() // Store the list

                        // Intent:

                        val intent =
                            Intent(this@MenuFormandoActivity, MainPerfilActivity2::class.java)
                        intent.putExtra("user", user as Serializable)
                        startActivity(intent)
                    } else {

                        // Fazer Toast

                        Toast.makeText(
                            applicationContext,
                            "Não foi possivel realizar a operação",
                            Toast.LENGTH_LONG
                        ).show()

                        //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {

                    // Fazer Toast

                    Toast.makeText(
                        applicationContext,
                        "Falha ao tentar aceder o servidor",
                        Toast.LENGTH_LONG
                    ).show()

                    //Log.i(TAG, "onFailure: ${t.message}")
                }
            })

        }


    }
}