package com.example.projectofinalteamjr

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.cursos.DetalhesCursoFormandoActivity
import com.example.projectofinalteamjr.databinding.ActivityCursosFormandoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class CursosFormandoActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCursosFormandoBinding.inflate(layoutInflater)
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

        val i = intent
        val listaCursos = i.getSerializableExtra("listaCursos") as List<Cursos>

        val listaNomesCursos = ArrayList<String>()

        for (curso in listaCursos){
            listaNomesCursos.add(curso.Nome)
        }
        val arrayAdapterCursosFormando = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaNomesCursos!!){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

        binding.cursosFormandoID.adapter = arrayAdapterCursosFormando

        binding.cursosFormandoID.setOnItemClickListener { parent, view, position, id ->

            myApi.getModulosCurso(position+1).enqueue(object : Callback<List<Modulos>> {
                override fun onResponse(
                    call: Call<List<Modulos>>,
                    response: Response<List<Modulos>>
                ) {
                    if (response.isSuccessful) {

                        var modulos = response.body() // Store the list

                        // Intent:
                        val intent = Intent(this@CursosFormandoActivity, DetalhesCursoFormandoActivity::class.java)
                        intent.putExtra("curso",listaCursos[position] as Serializable)
                        intent.putExtra("modulos", modulos as Serializable)
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

                override fun onFailure(call: Call<List<Modulos>>, t: Throwable) {

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

        binding.buttonCursosFormandoBack.setOnClickListener {
            finish()
        }


    }




}