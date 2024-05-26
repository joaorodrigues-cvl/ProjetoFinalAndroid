package com.example.projectofinalteamjr.cursos

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.CursoModulo
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.modulosList
import com.example.projectofinalteamjr.databinding.ActivityModulosDoCursoBinding
import com.example.projectofinalteamjr.modulos.AdicionarModuloAoCursoActivity
import com.example.projectofinalteamjr.modulos.DetalhesModuloActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class ModulosDoCursoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityModulosDoCursoBinding.inflate(layoutInflater)
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

        val modulosList=i.getSerializableExtra("Modulos") as List<Modulos>
        val cursoPosition=i.getIntExtra("cursoPosition",-1)

val listaNomesModulos = ArrayList<String>()

        for (modulo in modulosList) {
            listaNomesModulos.add(modulo.Nome)
        }

        val arrayAdapterNomesModulos = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1,
            listaNomesModulos
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(R.id.text1)
                textView.setTextColor(Color.WHITE) // cor do texto branco
                return view
            }
        }

        //val arrayAdapterDescricaoCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaDescricaoCursos!!)
        //val arrayAdapterHorasCursos = ArrayAdapter(this, R.layout.simple_list_item_1, listaHorasCursos!!)

        binding.LVModulosID.adapter = arrayAdapterNomesModulos
        //binding.decricaoCursosID.adapter = arrayAdapterDescricaoCursos
        //binding.horasCursosID.adapter = arrayAdapterHorasCursos

        binding.LVModulosID.setOnItemClickListener { parent, view, position, id ->
            /*val element = parent.getItemAtPosition(position) as String?
            val descricao = listaDescricaoModulos!!.get(position)
            val regime = listaRegimeModulos!!.get(position)
            val Totalhoras = listaHorasModulos!!.get(position)
            val idmodulo = position+1
            val intent = Intent(this, DetalhesModuloActivity::class.java)
            intent.putExtra("nomeModulo",element)
            intent.putExtra("descricaoModulo",descricao)
            intent.putExtra("regimeModulo", regime)
            intent.putExtra("horasModulo",Totalhoras)
            intent.putExtra("idModulo",idmodulo)
            startActivity(intent)*/

        }

        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.btnAddModuloToCurso.setOnClickListener {

            myApi.getModulos().enqueue(object : Callback<List<Modulos>> {
                override fun onResponse(
                    call: Call<List<Modulos>>,
                    response: Response<List<Modulos>>
                ) {
                    if (response.isSuccessful) {

                        var listaModulos = response.body() // Store the list

                        val i = Intent(this@ModulosDoCursoActivity,SelecionarModuloActivity::class.java)
                        i.putExtra("listaModulos",listaModulos as Serializable)
                        i.putExtra("cursoPosition",cursoPosition)
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

    }
}