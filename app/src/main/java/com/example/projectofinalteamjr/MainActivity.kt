package com.example.projectofinalteamjr

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.CursosActions
import com.example.projectofinalteamjr.api.CursosCallback
import com.example.projectofinalteamjr.api.Modulo
import com.example.projectofinalteamjr.api.ModulosActions
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.api.cursosList
import com.example.projectofinalteamjr.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val cursosApiActions = CursosActions()
       cursosApiActions.getApiCursos()

        for (curso in cursosList!!){
            Log.i("repostaMain", "onCreate: ${curso.Nome} ")
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