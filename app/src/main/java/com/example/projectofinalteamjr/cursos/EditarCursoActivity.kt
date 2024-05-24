package com.example.projectofinalteamjr.cursos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectofinalteamjr.MenuAdminActivity
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.Cursos
import com.example.projectofinalteamjr.api.CursosActions
import com.example.projectofinalteamjr.api.MyApi
import com.example.projectofinalteamjr.databinding.ActivityEditarCursoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditarCursoActivity : AppCompatActivity() {



    private val binding by lazy{
        ActivityEditarCursoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cursosActions = CursosActions()
        setContentView(binding.root)



        val intent = intent

        val nomeCurso = intent.getStringExtra("nomeCurso")
        val descricaoCurso = intent.getStringExtra("descricaoCurso")
        val horasCurso = intent.getIntExtra("horasCurso", 0)
        val idCurso = intent.getIntExtra("idCurso",0)

        binding.editNomeCurso.setText(nomeCurso)
        binding.editDescricaoCurso.setText(descricaoCurso)
        binding.editHorasCurso.setText(horasCurso.toString())

        binding.btnGravarEditCurso.setOnClickListener {
            val nome = binding.editNomeCurso.text.toString()
            val descricao = binding.editDescricaoCurso.text.toString()
            val totalHoras = binding.editHorasCurso.text.toString().toIntOrNull() ?: 0

            if (nome.isNotEmpty() && descricao.isNotEmpty() && totalHoras > 0) {
                val curso = Curso(nome, descricao, totalHoras)
                val resposta = cursosActions.atualizarCurso(idCurso, curso)

                Toast.makeText(this, resposta, Toast.LENGTH_SHORT).show()

                /*// Fetch the updated list of cursos from the API
                val BASE_URL = "http://10.0.2.2:8000/api/"
                val TAG: String = "CHECK_RESPONSE"
                val TAG2: String = "Metodo Post"

                var cursosNomeList = ArrayList<String>()
                var cursosDescricaoList = ArrayList<String>()
                var cursosHorasList = ArrayList<Int>()
                val api = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

                val myApi=api.create(MyApi::class.java);

                myApi.getCursos().enqueue(object : Callback<ArrayList<Cursos>> {
                    override fun onResponse(
                        call: Call<ArrayList<Cursos>>,
                        response: Response<ArrayList<Cursos>>
                    ) {
                        if (response.isSuccessful) {

                            var output = response.body() // Store the list
                            output?.let {
                                for (curso in it) {
                                    cursosNomeList.add(curso.Nome)
                                    cursosDescricaoList.add(curso.Descricao)
                                    cursosHorasList.add(curso.TotalHoras)

                                    // Process each curso object
                                    // You can access properties like curso.Nome here
                                }

                                // Intent:
                                val i: Intent = Intent(this@EditarCursoActivity, CursosActivity::class.java)
                                i.putExtra("listaNomesCursos", cursosNomeList)
                                i.putExtra("listaDescricaoCursos", cursosDescricaoList)
                                i.putExtra("listaHorasCursos", cursosHorasList)
                                startActivity(i)
                            }
                        } else {

                            // Fazer Toast

                            Toast.makeText(applicationContext, "Não foi possivel realizar a operação", Toast.LENGTH_LONG).show()

                            //Log.i(TAG, "Unsuccessful response: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Cursos>>, t: Throwable) {
                        // Handle the error here
                        Toast.makeText(applicationContext, "Erro na conexão: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                    }
                })*/

            } else {
                // Show error message to user
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonBack.setOnClickListener {
            val i = Intent(this, MenuAdminActivity::class.java)
            startActivity(i)
        }

    }
}