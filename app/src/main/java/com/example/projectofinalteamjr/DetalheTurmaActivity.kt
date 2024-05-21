package com.example.projectofinalteamjr

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.DetalhesTurma
import com.example.projectofinalteamjr.api.Turma
import com.example.projectofinalteamjr.api.Turmas
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding
import com.example.projectofinalteamjr.databinding.ActivityDetalheTurmaBinding

class DetalheTurmaActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityDetalheTurmaBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        var turma = i.getSerializableExtra("listaTurmas") as DetalhesTurma

        val listaNomesFormandos = ArrayList<String>()

        for (formando in turma.Formandos){
            listaNomesFormandos.add(formando.formandoNome)
        }


        val arrayAdapterFormandosTurma = ArrayAdapter(this, R.layout.simple_list_item_1, listaNomesFormandos)


        binding.editNomeTurma.text= turma.Turma.nome

        binding.editRegimeTurma.text=turma.Turma.regime

        binding.editLocalizacaoTurma.text=turma.Turma.localizacao

        binding.editDataInicio.text=turma.Turma.datainicio

        binding.editDataFim.text=turma.Turma.datafim

        binding.editTurmaCurso.text=turma.CursoTurma.cursoNome

        binding.editTurmaFormandos.adapter=arrayAdapterFormandosTurma


    }
}