package com.example.tdm2_td2

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.example.tdm2_td2.exercice4.Adapter
import com.example.tdm2_td2.exercice4.TodoService
import com.example.tdm2_td2.models.Todo
import com.google.gson.Gson

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        internal var Todos: List<Todo> = ArrayList<Todo>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val service: TodoService

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TodoService::class.java)
        val call = service.getAllTodos
        call.enqueue(
            object : Callback<List<Todo>> {
                override fun onFailure(call: Call<List<Todo>>?, t:Throwable?) {
                    Log.e("MainActivity", "Problem calling API {${t?.message}}")
                }

                override fun onResponse(call: Call<List<Todo>>?, response: Response<List<Todo>>?) {
                    response?.isSuccessful.let {
                       val todoList = findViewById<ListView>(R.id.todos_list)
                        Log.d("MainActivity", response?.body().toString())


                        val resultList = response?.body()
                        todoList.adapter = resultList?.let { it1 ->
                            Adapter(this@MainActivity,
                                it1
                            )
                        }
                    }
                }
            }
        )

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
