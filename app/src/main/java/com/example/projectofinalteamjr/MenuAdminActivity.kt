package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.Turmas
import com.example.projectofinalteamjr.databinding.ActivityMenuAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MenuAdminActivity : AppCompatActivity() {

    val BASE_URL = "http://10.0.2.2:8000/api/"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val myApi=api.create(MyApi::class.java);

    public var cursosNomeList = ArrayList<String>()
    public var cursosDescricaoList = ArrayList<String>()
    public var cursosHorasList = ArrayList<Int>()

    private val binding by lazy{
        ActivityMenuAdminBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.getCursos.setOnClickListener{

            myApi.getCursos().enqueue(object : Callback<ArrayList<Cursos>> {
                override fun onResponse(
                    call: Call<ArrayList<Cursos>>,
                    response: Response<ArrayList<Cursos>>
                ) {
                    if (response.isSuccessful) {

                        var output = response.body() // Store the list
                        output?.let {
                            for (curso in it) {
                                cursosNomeList.add(curso.Nome)
                                cursosDescricaoList.add(curso.Descricao)
                                cursosHorasList.add(curso.TotalHoras)

                                // Process each curso object
                                // You can access properties like curso.Nome here
                            }

                        }
                        // Intent:

                        val i: Intent = Intent(this@MenuAdminActivity, CursosActivity::class.java)
                        i.putExtra("listaNomesCursos", cursosNomeList)
                        i.putExtra("listaDescricaoCursos", cursosDescricaoList)
                        i.putExtra("listaHorasCursos", cursosHorasList)
                        startActivity(i)
                    } else {

                        // Fazer Toast

                        Toast.makeText(applicationContext, "Não foi possivel realizar a operação", Toast.LENGTH_LONG).show()

                        //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ArrayList<Cursos>>, t: Throwable) {

                    // Fazer Toast

                    Toast.makeText(applicationContext, "Falha ao tentar aceder o servidor", Toast.LENGTH_LONG).show()

                    //Log.i(TAG, "onFailure: ${t.message}")
                }
            })
        }


        binding.buttonTurmasID.setOnClickListener {


            myApi.getTurmas().enqueue(object : Callback<List<Turmas>> {
                override fun onResponse(
                    call: Call<List<Turmas>>,
                    response: Response<List<Turmas>>
                ) {
                    if (response.isSuccessful) {

                        var turmas = response.body()!! // Store the list

                        // Intent:

                        val i: Intent = Intent(this@MenuAdminActivity, TurmasActivity::class.java)
                        i.putExtra("listaTurmas", turmas as Serializable)
                        startActivity(i)
                    } else {

                        // Fazer Toast

                        Toast.makeText(applicationContext, "Não foi possivel realizar a operação", Toast.LENGTH_LONG).show()

                        //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<Turmas>>, t: Throwable) {

                    // Fazer Toast

                    Toast.makeText(applicationContext, "Falha ao tentar aceder o servidor", Toast.LENGTH_LONG).show()

                    //Log.i(TAG, "onFailure: ${t.message}")
                }

            })

        }

        binding.buttonLogout.setOnClickListener {
            val iLogout: Intent = Intent(this@MenuAdminActivity, MainActivity::class.java)
            startActivity(iLogout)
        }


    }
}