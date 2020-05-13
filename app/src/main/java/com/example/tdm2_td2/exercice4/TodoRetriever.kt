package com.example.tdm2_td2.exercice4

import com.example.tdm2_td2.models.Todo
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TodoRetriever {

    private val service: TodoService

    companion object {
        //1
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TodoService::class.java)
    }
//
//    fun getAllTodos(callback: Callback<Todo>) { //5
//        val call = service.getAllTodos
//        call.enqueue(callback)
//    }

}