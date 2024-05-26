package com.example.projectofinalteamjr.modulos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.MenuAdminActivity
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

    val BASE_URL = "http://10.0.2.2:8000/api/"
    val TAG: String = "CHECK_RESPONSE"
    val TAG2: String = "Metodo Post"

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val myApi = api.create(MyApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val modulosActions = ModulosActions()
        setContentView(binding.root)


        var listaNomesModulos = ArrayList<String>()
        var listaDescricaoModulos = ArrayList<String>()
        var listaRegimeModulos = ArrayList<String>()
        var listaHorasModulos = ArrayList<Int>()


        val intent = intent

        val nomeModulo = intent.getStringExtra("nomeModulo")
        val descricaoModulo = intent.getStringExtra("descricaoModulo")
        val regimeModulo = intent.getStringExtra("regimeModulo")
        val horasModulo = intent.getIntExtra("horasModulo", 0)
        val idModulo = intent.getIntExtra("idModulo", -1)

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
                val modulo = Modulo(nome, descricao, horas, regime)

                myApi.atualizarModulo(idModulo, modulo).enqueue(object : Callback<Modulo> {
                    override fun onResponse(call: Call<Modulo>, response: Response<Modulo>) {
                        if (response.isSuccessful) {
                            val moduloAtualizado = response.body()

                            Toast.makeText(
                                applicationContext,
                                "Modulo atualizado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(
                                    this@EditarModuloActivity,
                                    MenuAdminActivity::class.java
                                )
                            )

                        } else {

                            Toast.makeText(applicationContext, "Erro.", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<Modulo>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Não foi possivel estabelecer ligação com base de dados",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

                binding.buttonBack2.setOnClickListener {
                    finish()

                }
            }
        }
    }
}