package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.CursosActions
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityMenuAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuAdminActivity : AppCompatActivity() {


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

           val cursoAction = CursosActions()

           val listaCursos = cursoAction.getApiCursos()
            if (listaCursos != null) {
                for (curso in listaCursos) {
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






            /*val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            val myApi=api.create(MyApi::class.java);

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
*/



        }

        binding.buttonLogout.setOnClickListener {
            val iLogout: Intent = Intent(this@MenuAdminActivity, MainActivity::class.java)
            startActivity(iLogout)
        }

    }
}