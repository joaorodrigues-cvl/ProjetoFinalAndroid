package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.api.Modulo
import com.example.projectofinalteamjr.api.ModulosActions
import com.example.projectofinalteamjr.databinding.ActivityAdicionarModuloBinding

class AdicionarModuloActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAdicionarModuloBinding
    private val modulosActions = ModulosActions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicionarModuloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGravarModulo.setOnClickListener {
            val nome = binding.editNomeModulo.text.toString()
            val descricao = binding.editDescricaoModulo.text.toString()
            val regimeModulo = binding.editTipoRegime.text.toString()
            val horas = binding.editHorasModulo.text.toString().toIntOrNull()?: 0


            if (nome.isNotEmpty() && descricao.isNotEmpty() && horas > 0 && regimeModulo.isNotEmpty()) {
                val modulo = Modulo(nome, descricao, regimeModulo, horas)
                modulosActions.sendRequestModulos(modulo)
                startActivity(Intent(this, MenuAdminActivity::class.java))
            } else {
                // Show error message to user
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }


    }
}