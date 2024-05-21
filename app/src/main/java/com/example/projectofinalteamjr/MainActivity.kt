package com.example.projectofinalteamjr

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.projectofinalteamjr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

binding.buttonSubmit.setOnClickListener {
    var user = binding.editUser.text.toString()
    var pass = binding.editPassword.text.toString()

    if (user == "admin" || user == "ADMIN" && pass == "123"){
        // vai para menu admin
        val intentAdmin: Intent = Intent(this@MainActivity, MenuAdminActivity::class.java)
        startActivity(intentAdmin)

    }else if (user == "formando" && pass == "123"){
        // vai para o menu formando
        val intentFormando: Intent = Intent(this@MainActivity, MenuFormandoActivity::class.java)
        startActivity(intentFormando)
    }else{
        Toast.makeText(applicationContext, "Login inválido. Tente novamente.", Toast.LENGTH_SHORT).show()
        binding.editUser.text.clear()
        binding.editPassword.text.clear()
    }
}










     /*   val modulo = Modulo(
            Nome = "Engenharia de Software",
            Descricao = "Módulo de Engenharia de Software",
            Horas = 45,
            Regime_modulo = "Presencial"
        )

        val moduloApiAction = ModulosActions()
        moduloApiAction.getApiModulos()
        moduloApiAction.sendRequestModulos(modulo)*/

       /* // Rotina de criação de um curso novo

        val curso = Curso(
            Nome = "Test3",
            Descricao = "Test3",
            TotalHoras = 50
        )

        val cursoAction = CursosActions() // Cria um novo objeto do tipo cursoAction
        cursoAction.sendRequestCursos(curso) //chama a função desse novo objeto para enviar um novo curso para a DB

        // .............................................................................

        // Rotina para buscar os cursos

        cursoAction.getApiCursos() // chama a função desse novo objeto para buscar os cursos*/

    }
}