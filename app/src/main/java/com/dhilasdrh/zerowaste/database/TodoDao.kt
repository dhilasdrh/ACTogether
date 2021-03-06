package com.dhilasdrh.solutionchallenge.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dhilasdrh.zerowaste.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun getTodos(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}