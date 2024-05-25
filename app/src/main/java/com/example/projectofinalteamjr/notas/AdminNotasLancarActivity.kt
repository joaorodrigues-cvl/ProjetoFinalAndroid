package com.example.projectofinalteamjr.notas

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.Parametro
import com.example.projectofinalteamjr.databinding.ActivityAdminNotasLancarBinding
import com.example.projectofinalteamjr.databinding.ActivityAdminNotasSelecionarModulosBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminNotasLancarActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdminNotasLancarBinding.inflate(layoutInflater)
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

        val listaParametros = i.getSerializableExtra("listaParametros") as List<Parametro>
        val nomeFormando = i.getStringExtra("formandoNome")
        val formandoTurmaID = i.getIntExtra("formandoTurmaID",-1)
        val moduloNome = i.getStringExtra("nomeModulo")
        val moduloID = i.getIntExtra("moduloID",-1)

        val listaDisplayParametros = ArrayList<String>()

        for (parametro in listaParametros){
            val nome = parametro.nome
            val ponderacao = parametro.ponderacao
            listaDisplayParametros.add("$nome  --->  $ponderacao")
        }
        val arrayAdapterparametros =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDisplayParametros)

        binding.LVParametros.adapter = arrayAdapterparametros

        binding.idCaminho.text = "$nomeFormando -> $moduloNome"

        binding.buttonSubmit.setOnClickListener {
            val notaParametro = binding.notaParametroID.text.toString().toDouble()


        }






    }
}