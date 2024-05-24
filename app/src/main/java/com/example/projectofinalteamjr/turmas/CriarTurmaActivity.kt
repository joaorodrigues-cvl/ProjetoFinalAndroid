package com.example.projectofinalteamjr.turmas

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.databinding.ActivityCriarTurmaBinding
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

        tvDateInicio = findViewById(R.id.TV_datepickerInicio)
        btnDateInicio = findViewById(R.id.btn_datepickerInicio)
        tvDateFim = findViewById(R.id.TV_datepickerFim)
        btnDateFim = findViewById(R.id.btn_datepickerFim)

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