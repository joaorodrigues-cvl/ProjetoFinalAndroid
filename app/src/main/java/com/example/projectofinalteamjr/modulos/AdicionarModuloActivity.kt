package com.example.projectofinalteamjr.modulos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.MenuAdminActivity
import com.example.projectofinalteamjr.api.Modulo
import com.example.projectofinalteamjr.api.ModulosActions
import com.example.projectofinalteamjr.databinding.ActivityAdicionarModuloBinding

class AdicionarModuloActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityAdicionarModuloBinding.inflate(layoutInflater)
    }

    val modulosActions = ModulosActions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnGravarModulo.setOnClickListener {
            val nome = binding.editNomeModulo.text.toString()
            val descricao = binding.editDescricaoModulo.text.toString()
            val horas = binding.editHorasModulo.text.toString().toIntOrNull()?: 0
            val regimeModulo = binding.editTipoRegime.text.toString()

            if (nome.isNotEmpty() && descricao.isNotEmpty() && horas > 0 && regimeModulo.isNotEmpty()) {
                val modulo = Modulo(nome, descricao, horas, regimeModulo)
                modulosActions.sendRequestModulos(this, modulo)
                startActivity(Intent(this, MenuAdminActivity::class.java))
            } else {
                // Show error message to user
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonBack.setOnClickListener {
            val iBack: Intent = Intent(this@AdicionarModuloActivity, MenuAdminActivity::class.java)
            startActivity(iBack)
        }

    }
}