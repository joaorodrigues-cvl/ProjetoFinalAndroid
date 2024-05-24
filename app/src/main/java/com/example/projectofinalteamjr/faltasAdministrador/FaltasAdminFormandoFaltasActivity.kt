package com.example.projectofinalteamjr.faltasAdministrador

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.databinding.ActivityFaltasAdminFormandoFaltasBinding
import java.text.SimpleDateFormat
import java.util.Locale

class FaltasAdminFormandoFaltasActivity : AppCompatActivity() {

    private lateinit var tvDataFalta : TextView
    private lateinit var btnDataFalta : Button

    private val binding by lazy {
        ActivityFaltasAdminFormandoFaltasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        tvDataFalta = findViewById(R.id.TV_datepicker)
        btnDataFalta = findViewById(R.id.btn_datepicker)



        var calendarioFalta = Calendar.getInstance()
        val datePickerFalta = DatePickerDialog.OnDateSetListener{ view, anoInicio, mesInicio, diaDoMesInicio ->
            calendarioFalta.set(Calendar.YEAR, anoInicio)
            calendarioFalta.set(Calendar.MONTH, mesInicio)
            calendarioFalta.set(Calendar.DAY_OF_MONTH, diaDoMesInicio)
            updateLable(calendarioFalta)
        }


        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@FaltasAdminFormandoFaltasActivity, FaltasAdminModuloListActivity::class.java)
            startActivity(iBack)
        }

        btnDataFalta.setOnClickListener{
            DatePickerDialog(this, datePickerFalta, calendarioFalta.get(Calendar.YEAR),
                calendarioFalta.get(Calendar.MONTH), calendarioFalta.get(Calendar.DAY_OF_MONTH)).show()
        }


    }
    private fun updateLable(calendarioFalta: Calendar) {
        val formatoData = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formatoData, Locale.UK)
        tvDataFalta.setText(sdf.format(calendarioFalta.time))
    }
}