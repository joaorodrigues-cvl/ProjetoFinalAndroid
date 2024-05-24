package com.example.projectofinalteamjr.turmas

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.Turma
import com.example.projectofinalteamjr.databinding.ActivityCriarTurmaBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

class CriarTurmaActivity : AppCompatActivity() {

    private lateinit var tvDateInicio : TextView
    private lateinit var btnDateInicio : Button
    private lateinit var tvDateFim : TextView
    private lateinit var btnDateFim : Button

    private val binding by lazy {
        ActivityCriarTurmaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        tvDateInicio = binding.TVDatepickerInicio
        btnDateInicio = binding.btnDatepickerInicio
        tvDateFim = binding.TVDatepickerFim
        btnDateFim = binding.btnDatepickerFim

        var calendarioInicio = Calendar.getInstance()
        val datePickerInicio = DatePickerDialog.OnDateSetListener{view, anoInicio, mesInicio, diaDoMesInicio ->
            calendarioInicio.set(Calendar.YEAR, anoInicio)
            calendarioInicio.set(Calendar.MONTH, mesInicio)
            calendarioInicio.set(Calendar.DAY_OF_MONTH, diaDoMesInicio)
            updateLableInicio(calendarioInicio)
        }

        var calendarioFim = Calendar.getInstance()
        val datePickerFim = DatePickerDialog.OnDateSetListener{view, anoFim, mesFim, diaDoMesFim ->
            calendarioFim.set(Calendar.YEAR, anoFim)
            calendarioFim.set(Calendar.MONTH, mesFim)
            calendarioFim.set(Calendar.DAY_OF_MONTH, diaDoMesFim)
            updateLableFim(calendarioFim)
        }
        btnDateInicio.setOnClickListener{
            DatePickerDialog(this, datePickerInicio, calendarioInicio.get(Calendar.YEAR),
                calendarioInicio.get(Calendar.MONTH), calendarioInicio.get(Calendar.DAY_OF_MONTH)).show()
        }
        btnDateFim.setOnClickListener{
            DatePickerDialog(this, datePickerFim, calendarioFim.get(Calendar.YEAR),
                calendarioFim.get(Calendar.MONTH), calendarioFim.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.btnGravarTurma.setOnClickListener {
            // cria uma instanciacao da classe turma

            val novaTurma = Turma(2, "Turma DA Braga", "Braga", "2024-10-01","2025-05-31","Presencial")


            val client = OkHttpClient.Builder().build()   // adicionado para funcioanr..
            val builder = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(MyApi::class.java)


            val call = builder.criarTurma(novaTurma) // cria um novo turma do tipo turma de acordo com os dados anteriores

            call.enqueue(object : Callback<Turma> {
                override fun onResponse(call: Call<Turma>, response: Response<Turma>) {
                    // Handle successful response
                    if (response.isSuccessful) {
                        val TurmaCriada = response.body()



                    } else {

                    }
                }

                override fun onFailure(call: Call<Turma>, t: Throwable) {
                    // Handle failure

                }
            })

        }




        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@CriarTurmaActivity, TurmasActivity::class.java)
            startActivity(iBack)
        }

    }

    private fun updateLableInicio(calendarioInicio: Calendar) {
        val formatoDataInicio = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formatoDataInicio, Locale.UK)
        tvDateInicio.setText(sdf.format(calendarioInicio.time))
    }
    private fun updateLableFim(calendarioFim: Calendar) {
        val formatoDataFim = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formatoDataFim , Locale.UK)
        tvDateFim.setText(sdf.format( calendarioFim.time))
    }
}