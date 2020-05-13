package com.example.tdm2_td2.exercice4

import com.example.tdm2_td2.models.Todo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoService {

    @GET("todos/{id}")
    fun getTodo(@Path("id") id: String): Call<Todo>

    @get:GET("todos")
    val getAllTodos: Call<List<Todo>>
}