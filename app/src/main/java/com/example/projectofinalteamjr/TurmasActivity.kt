package com.example.projectofinalteamjr

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.DetalhesTurma
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.Turmas
import com.example.projectofinalteamjr.databinding.ActivityMenuAdminBinding
import com.example.projectofinalteamjr.databinding.ActivityTurmasBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class TurmasActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityTurmasBinding.inflate(layoutInflater)


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val BASE_URL = "http://10.0.2.2:8000/api/"

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val myApi=api.create(MyApi::class.java);

        val i = intent

        val listaTurmas = i.getSerializableExtra("listaTurmas") as List<Turmas>

        val listaNomesTurmas = ArrayList<String>()

        for (turma in listaTurmas){
            listaNomesTurmas.add(turma.nome)
        }


        val arrayAdapterTurmas = ArrayAdapter(this, R.layout.simple_list_item_1, listaNomesTurmas)

        binding.nomeTurmasID.adapter=arrayAdapterTurmas

        binding.nomeTurmasID.setOnItemClickListener { parent, view, position, id ->
            myApi.detalhesTurma(position+1).enqueue(object : Callback<List<DetalhesTurma>> {
                override fun onResponse(
                    call: Call<List<DetalhesTurma>>,
                    response: Response<List<DetalhesTurma>>
                ) {
                    if (response.isSuccessful) {

                        var turma = response.body()!! // Store the list

                        // Intent:

                        val i: Intent = Intent(this@TurmasActivity, DetalheTurmaActivity::class.java)
                        i.putExtra("listaTurmas", turma as Serializable)
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

                override fun onFailure(call: Call<List<DetalhesTurma>>, t: Throwable) {

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
            val iBack: Intent = Intent(this@TurmasActivity,MenuAdminActivity::class.java)
            startActivity(iBack)
        }

    }
}