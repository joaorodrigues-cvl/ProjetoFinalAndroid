package com.example.projectofinalteamjr.modulos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Modulo
import com.example.projectofinalteamjr.api.Modulos
import com.example.projectofinalteamjr.api.ModulosActions
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityEditarModuloBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditarModuloActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityEditarModuloBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val modulosActions = ModulosActions()
        setContentView(binding.root)


        val intent = intent

        val nomeModulo = intent.getStringExtra("nomeModulo")
        val descricaoModulo = intent.getStringExtra("descricaoModulo")
        val regimeModulo = intent.getStringExtra("regimeModulo")
        val horasModulo = intent.getIntExtra("horasModulo", 0)
        val idModulo = intent.getIntExtra("idModulo", 0)

        binding.editNomeModulo.setText(nomeModulo)
        binding.editDescricaoModulo.setText(descricaoModulo)
        binding.editRegimeModulo.setText(regimeModulo)
        binding.editHorasModulo.setText(horasModulo.toString())

        binding.btnGravarEditModulo2.setOnClickListener {
            val nome = binding.editNomeModulo.text.toString()
            val descricao = binding.editDescricaoModulo.text.toString()
            val regime = binding.editRegimeModulo.text.toString()
            val horas = binding.editHorasModulo.text.toString().toIntOrNull() ?: 0


            if (nome.isNotEmpty() && descricao.isNotEmpty() && regime.isNotEmpty() && horas > 0) {
                val modulo = Modulo(nome, descricao,  horas, regime)
                modulosActions.atualizarModulo(idModulo, modulo)


                val BASE_URL = "http://10.0.2.2:8000/api/"
                val TAG: String = "CHECK_RESPONSE"
                val TAG2: String = "Metodo Post"

                var listaNomesModulos = ArrayList<String>()
                var listaDescricaoModulos = ArrayList<String>()
                var listaRegimeModulos = ArrayList<String>()
                var listaHorasModulos = ArrayList<Int>()
                val api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

                val myApi = api.create(MyApi::class.java)
                myApi.getModulos().enqueue(object : Callback<List<Modulos>> {
                    override fun onResponse(
                        call: Call<List<Modulos>>,
                        response: Response<List<Modulos>>
                    ) {
                        if (response.isSuccessful) {

                            var output = response.body() // Store the list
                            output?.let {
                                for (modulo in it) {
                                    listaNomesModulos.add(modulo.Nome)
                                    listaDescricaoModulos.add(modulo.Descricao)
                                    listaRegimeModulos.add(modulo.Regime_modulo)
                                    listaHorasModulos.add(modulo.Horas)

                            }
                            // Intent:

                            val i: Intent = Intent(this@EditarModuloActivity, ModulosActivity::class.java)
                            i.putExtra("listaNomesModulos", listaNomesModulos)
                            i.putExtra("listaDescricaoModulos", listaDescricaoModulos)
                            i.putExtra("listaRegimeModulos", listaRegimeModulos)
                            i.putExtra("listaHorasModulos", listaHorasModulos)
                            startActivity(i)
                                }
                        } else {

                            // Fazer Toast

                            Toast.makeText(applicationContext, "Não foi possivel realizar a operação", Toast.LENGTH_LONG).show()

                            //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<List<Modulos>>, t: Throwable) {

                        // Fazer Toast

                        Toast.makeText(applicationContext, "Falha ao tentar aceder o servidor", Toast.LENGTH_LONG).show()

                        //Log.i(TAG, "onFailure: ${t.message}")
                    }

                })
            } else {
                // Show error message to user
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }


        }
        binding.buttonBack2.setOnClickListener {
            finish()

        }}
}