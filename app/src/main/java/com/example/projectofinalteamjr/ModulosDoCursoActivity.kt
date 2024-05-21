package com.example.projectofinalteamjr

import android.R
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
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityModulosDoCursoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModulosDoCursoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityModulosDoCursoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent
        val BASE_URL = "http://10.0.2.2:8000/api/"

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val myApi = api.create(MyApi::class.java);

         var modulosNomeList = ArrayList<String>()
         var modulosDescricaoList = ArrayList<String>()
         var modulosRegimeList = ArrayList<String>()
         var modulosHorasList = ArrayList<Int>()


        var listaNomesModulos = i.getStringArrayListExtra("listaNomesModulos")
        var listaDescricaoModulos = i.getStringArrayListExtra("listaDescricaoModulos")
        var listaRegimeModulos = i.getStringArrayListExtra("listaRegimeModulos")
        var listaHorasModulos = i.getIntegerArrayListExtra("listaHorasModulos")!!

        val arrayAdapterNomesModulos = object : ArrayAdapter<String>(this, R.layout.simple_list_item_1, listaNomesModulos!!) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
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
            val element = parent.getItemAtPosition(position) as String?
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
            startActivity(intent)

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

                        var output = response.body() // Store the list
                        output?.let {
                            for (modulo in it) {
                                modulosNomeList.add(modulo.Nome)
                                modulosDescricaoList.add(modulo.Descricao)
                                modulosRegimeList.add(modulo.Regime_modulo)
                                modulosHorasList.add(modulo.Horas)

                            }

                        }
                        // Intent:

                        val i: Intent = Intent(
                            this@ModulosDoCursoActivity,
                            AdicionarModuloAoCursoActivity::class.java
                        )
                        i.putExtra("listaNomesModulos", modulosNomeList)
                        i.putExtra("listaDescricaoModulos", modulosDescricaoList)
                        i.putExtra("listaRegimeModulos", modulosRegimeList)
                        i.putExtra("listaHorasModulos", modulosHorasList)
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