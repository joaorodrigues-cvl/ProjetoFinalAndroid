package com.example.projectofinalteamjr.notas

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.Parametro
import com.example.projectofinalteamjr.databinding.ActivityAdminNotasSelecionarFormandoBinding
import com.example.projectofinalteamjr.databinding.ActivityAdminNotasSelecionarModulosBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class AdminNotasSelecionarModulosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdminNotasSelecionarModulosBinding.inflate(layoutInflater)
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

        val listaModulos = i.getSerializableExtra("listaModulosFormando") as List<Modulos>
        val formandoNome = i.getStringExtra("nomeFormando")
        val formandoTurmaID = i.getIntExtra("formandoTurmaID",-1)

        val listaNomesModulos = ArrayList<String>()
        val listaModuloID = ArrayList<Int>()

        for (modulo in listaModulos){
            listaNomesModulos.add(modulo.Nome)
            listaModuloID.add(modulo.ModuloID)
        }

        binding.textNomeFormando.text = formandoNome

        val arrayAdapterNomesModulos =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNomesModulos)

        binding.LVModulosFormando.adapter = arrayAdapterNomesModulos

        binding.LVModulosFormando.setOnItemClickListener { parent, view, position, id ->
            val moduloNome = listaNomesModulos[position]
            val moduloID = listaModuloID[position]
            myApi.getParametros().enqueue(object : Callback<List<Parametro>> {
                override fun onResponse(
                    call: Call<List<Parametro>>,
                    response: Response<List<Parametro>>
                ) {
                    if (response.isSuccessful) {

                        var parametros = response.body()!! // Store the list

                        // Intent:

                        val i: Intent = Intent(this@AdminNotasSelecionarModulosActivity, AdminNotasLancarActivity::class.java)
                        i.putExtra("listaParametros", parametros as Serializable)
                        i.putExtra("formandoNome", formandoNome)
                        i.putExtra("nomeModulo",moduloNome)
                        i.putExtra("formandoTurmaID",formandoTurmaID)
                        i.putExtra("moduloID",moduloID)
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

                override fun onFailure(call: Call<List<Parametro>>, t: Throwable) {

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
    finish()
}
    }
}