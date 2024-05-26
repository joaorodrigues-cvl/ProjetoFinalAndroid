package com.example.projectofinalteamjr.cursos

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.MenuAdminActivity
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.CursoModulo
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityModulosDoCursoBinding
import com.example.projectofinalteamjr.databinding.ActivitySelecionarModuloBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelecionarModuloActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySelecionarModuloBinding.inflate(layoutInflater)
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

        val i = intent

        val listaModulos = i.getSerializableExtra("listaModulos") as List<Modulos>
        val cursoPosition = i.getIntExtra("cursoPosition", -1)

        val listaNomesModulos = ArrayList<String>()

        for (modulo in listaModulos) {
            listaNomesModulos.add(modulo.Nome)
        }

        val arrayAdapterNomesModulos = object :
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNomesModulos) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

        binding.nomeModulos.adapter = arrayAdapterNomesModulos

        var moduloID: Int = -1

        binding.nomeModulos.setOnItemClickListener { parent, view, position, id ->
            moduloID = position + 1
        }

        binding.buttonOk.setOnClickListener {
            myApi.adicionarModuloCurso(cursoPosition + 1, moduloID).enqueue(object :
                Callback<CursoModulo> {
                override fun onResponse(
                    call: Call<CursoModulo>,
                    response: Response<CursoModulo>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Modulo adicionado com sucesso",
                            Toast.LENGTH_LONG
                        ).show()

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

                override fun onFailure(call: Call<CursoModulo>, t: Throwable) {

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

        binding.buttonBack.setOnClickListener {
            startActivity(Intent(this,MenuAdminActivity::class.java))
        }
    }
}