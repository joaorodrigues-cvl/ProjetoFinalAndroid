package com.example.projectofinalteamjr.cursos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        .build();

    val myApi = api.create(MyApi::class.java);

    public var modulosNomeList = ArrayList<String>()
    public var modulosDescricaoList = ArrayList<String>()
    public var modulosRegimeList = ArrayList<String>()
    public var modulosHorasList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val intent = intent

        val nomeCurso = intent.getStringExtra("nomeCurso")
        val descricaoCurso = intent.getStringExtra("descricaoCurso")
        val horasCurso = intent.getIntExtra("horasCurso", 0)
        binding.editNomeCurso.text = nomeCurso
        binding.editDescricaoCurso.text = descricaoCurso
        binding.editHorasCurso.text = horasCurso.toString()

        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@DetalhesCursoActivity, CursosActivity::class.java)
            startActivity(iBack)
        }

        binding.btnModulosCurso.setOnClickListener {


            myApi.getModulosCurso(1).enqueue(object : Callback<List<Modulos>> {
                override fun onResponse(
                    call: Call<List<Modulos>>,
                    response: Response<List<Modulos>>
                ) {
                    if (response.isSuccessful) {

                        var modulos = response.body() // Store the list
                        // Intent:

                        val i: Intent = Intent(this@DetalhesCursoActivity, ModulosDoCursoActivity::class.java)
                        i.putExtra("Modulos", modulos as Serializable)
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
            val nomeCurso = intent.getStringExtra("nomeCurso")
            val descricaoCurso = intent.getStringExtra("descricaoCurso")
            val horasCurso = intent.getIntExtra("horasCurso", 0)
            val idCurso = intent.getIntExtra("idCurso", -1)

            val intentEditarCurso = Intent(this, EditarCursoActivity::class.java)
            intentEditarCurso.putExtra("nomeCurso", nomeCurso)
            intentEditarCurso.putExtra("descricaoCurso", descricaoCurso)
            intentEditarCurso.putExtra("horasCurso", horasCurso)
            intentEditarCurso.putExtra("idCurso", idCurso)
            startActivity(intentEditarCurso)
        }
    }
}