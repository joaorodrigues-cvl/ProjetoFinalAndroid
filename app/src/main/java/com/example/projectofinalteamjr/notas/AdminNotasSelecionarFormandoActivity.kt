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
import com.example.projectofinalteamjr.api.DetalhesTurma
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.Turmas
import com.example.projectofinalteamjr.databinding.ActivityAdminNotasSelecionarFormandoBinding
import com.example.projectofinalteamjr.databinding.ActivityAdminNotasSelecionarTurmaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class AdminNotasSelecionarFormandoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdminNotasSelecionarFormandoBinding.inflate(layoutInflater)
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

        val turma = i.getSerializableExtra("turma") as DetalhesTurma

        val listaFormandos = turma.Formandos

        val listaNomesFormandos = ArrayList<String>()
        val listaUserIDFormando = ArrayList<Int>()

        for (formando in listaFormandos){
            listaNomesFormandos.add(formando.formandoNome)
            listaUserIDFormando.add(formando.userID)
        }

        val arrayAdapterNomesFormandos =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNomesFormandos)



        binding.LVFormandosTurma.adapter=arrayAdapterNomesFormandos


        binding.LVFormandosTurma.setOnItemClickListener { parent, view, position, id ->
            val formandoNome = listaNomesFormandos[position]
            val userID = listaUserIDFormando[position]

            myApi.getModulosFormando(userID).enqueue(object : Callback<List<Modulos>> {
                override fun onResponse(
                    call: Call<List<Modulos>>,
                    response: Response<List<Modulos>>
                ) {
                    if (response.isSuccessful) {

                        var modulos = response.body()!! // Store the list

                        // Intent:

                        val i: Intent = Intent(this@AdminNotasSelecionarFormandoActivity, AdminNotasSelecionarModulosActivity::class.java)
                        i.putExtra("listaModulosFormando", modulos as Serializable)
                        i.putExtra("nomeFormando", formandoNome)
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