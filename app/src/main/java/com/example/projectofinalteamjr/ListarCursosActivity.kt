import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectofinalteamjr.R
import com.example.projectofinalteamjr.api.Curso
import com.example.projectofinalteamjr.api.CursoAdapter
import com.example.projectofinalteamjr.api.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListarCursosActivity : AppCompatActivity() {
    private val BASE_URL = "http://10.0.2.2:8000/api/"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CursoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_cursos)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        obterCursos()
    }

    private fun obterCursos() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getCursos().enqueue(object : Callback<List<Curso>> {
            override fun onResponse(call: Call<List<Curso>>, response: Response<List<Curso>>) {
                if (response.isSuccessful) {
                    response.body()?.let { cursos ->
                        adapter = CursoAdapter(cursos)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Curso>>, t: Throwable) {
                Log.e("ListarCursosActivity", "Erro ao obter cursos: ${t.message}")
            }
        })
    }
}