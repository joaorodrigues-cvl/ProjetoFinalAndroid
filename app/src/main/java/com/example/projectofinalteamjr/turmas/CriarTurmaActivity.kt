package com.example.projectofinalteamjr.turmas

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.MenuAdminActivity
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.Turma
import com.example.projectofinalteamjr.cursos.DetalhesCursoActivity
import com.example.projectofinalteamjr.databinding.ActivityCriarTurmaBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.Locale

class CriarTurmaActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityCriarTurmaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        val listaCursos = i.getSerializableExtra("listaCursos") as ArrayList<Cursos>

        val listaNomesCursos = ArrayList<String>()

        for (curso in listaCursos){
            listaNomesCursos.add(curso.Nome)
        }

        val arrayAdapterNomesCursos = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNomesCursos!!) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                return view
            }
        }

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
        binding.TVDatepickerInicio.setOnClickListener{
            DatePickerDialog(this, datePickerInicio, calendarioInicio.get(Calendar.YEAR),
                calendarioInicio.get(Calendar.MONTH), calendarioInicio.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.TVDatepickerFim.setOnClickListener{
            DatePickerDialog(this, datePickerFim, calendarioFim.get(Calendar.YEAR),
                calendarioFim.get(Calendar.MONTH), calendarioFim.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.listaCursosTurmaID.adapter=arrayAdapterNomesCursos

        var cursoID : Int = -1

        binding.listaCursosTurmaID.setOnItemClickListener { parent, view, position, id ->
               cursoID = position+1
        }


        binding.btnGravarTurma.setOnClickListener {
            // Ler dados

            val nomeTurma = binding.editNomeTurma.text.toString()
            val localizacaoTurma = binding.editLocalizacaoTurma.text.toString()
            val datainicioTurma = binding.TVDatepickerInicio.text.toString()
            val datafimTurma = binding.TVDatepickerFim.text.toString()
            val regimeTurma = binding.editTipoRegime.text.toString()




            // cria uma instanciacao da classe turma

            val novaTurma = Turma(cursoID, nomeTurma, localizacaoTurma, datainicioTurma,datafimTurma,regimeTurma)


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

                        Toast.makeText(applicationContext, "Turma gravada com sucesso!", Toast.LENGTH_SHORT).show()

                        val iback = Intent(this@CriarTurmaActivity, MenuAdminActivity::class.java)
                        startActivity(iback)

                    } else {

                        Toast.makeText(applicationContext, "Erro na submissão dos dados", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<Turma>, t: Throwable) {
                    // Handle failure

                    Toast.makeText(applicationContext, "Erro no acesso à base de dados", Toast.LENGTH_SHORT).show()

                }
            })

        }




        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@CriarTurmaActivity, TurmasActivity::class.java)
            startActivity(iBack)
        }

    }

    private fun updateLableInicio(calendarioInicio: Calendar) {
        val formatoDataInicio = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formatoDataInicio, Locale.UK)
       binding.TVDatepickerInicio.setText(sdf.format(calendarioInicio.time))
    }
    private fun updateLableFim(calendarioFim: Calendar) {
        val formatoDataFim = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(formatoDataFim , Locale.UK)
        binding.TVDatepickerFim.setText(sdf.format( calendarioFim.time))
    }
}