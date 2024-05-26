package com.example.projectofinalteamjr.cursos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityDetalhesCursoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class DetalhesCursoActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityDetalhesCursoBinding.inflate(layoutInflater)
    }
    val BASE_URL = "http://10.0.2.2:8000/api/"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val myApi = api.create(MyApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val intent = intent

        val cursoPosition = intent.getIntExtra("cursoPosition", -1)
        val listaCursos = intent.getSerializableExtra("listaCursos") as List<Cursos>

        val cursoSelecionado = listaCursos[cursoPosition]

        binding.editNomeCurso.text = cursoSelecionado.Nome
        binding.editDescricaoCurso.text = cursoSelecionado.Descricao
        binding.editHorasCurso.text = cursoSelecionado.TotalHoras.toString()

        binding.buttonBack.setOnClickListener {
            val i: Intent = Intent(this@DetalhesCursoActivity, CursosActivity::class.java)
            i.putExtra("listaCursos", listaCursos as Serializable)
            startActivity(i)
        }

        binding.btnModulosCurso.setOnClickListener {

            myApi.getModulosCurso(cursoSelecionado.cursoID).enqueue(object : Callback<List<Modulos>> {
                override fun onResponse(
                    call: Call<List<Modulos>>,
                    response: Response<List<Modulos>>
                ) {
                    if (response.isSuccessful) {

                        var modulos = response.body() // Store the list
                        // Intent:

                        val i: Intent = Intent(this@DetalhesCursoActivity, ModulosDoCursoActivity::class.java)
                        i.putExtra("Modulos", modulos as Serializable)
                        i.putExtra("cursoPosition", cursoPosition)
                        startActivity(i)

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
        binding.btnEditCurso.setOnClickListener {

            val intentEditarCurso = Intent(this, EditarCursoActivity::class.java)
            intentEditarCurso.putExtra("curso", cursoSelecionado as Serializable)
            startActivity(intentEditarCurso)
        }
    }
}