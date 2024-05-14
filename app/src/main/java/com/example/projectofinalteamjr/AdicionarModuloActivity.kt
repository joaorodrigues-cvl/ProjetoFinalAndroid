package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Modulo
import com.example.projectofinalteamjr.api.ModulosActions
import com.example.projectofinalteamjr.databinding.ActivityAdicionarModuloBinding
import com.example.projectofinalteamjr.databinding.ActivityCursosBinding

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
            val horas = binding.editHorasModulo.text.toString().toIntOrNull()?: 0
            val regimeModulo = binding.editTipoRegime.text.toString()

            if (nome.isNotEmpty() && descricao.isNotEmpty() && horas > 0 && regimeModulo.isNotEmpty()) {
                val modulo = Modulo(nome, descricao, horas, regimeModulo)
                modulosActions.sendRequestModulos(modulo)
                startActivity(Intent(this, MenuAdminActivity::class.java))
            } else {
                // Show error message to user
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }


    }
}