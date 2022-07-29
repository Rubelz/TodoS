package com.rubelz.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rubelz.myapplication.data.model.Todo

@Dao
interface TodoDao {

    @Query("Select * From Todo")
    fun getAllTodos(): LiveData<List<Todo>>

    @Query("Select * From Todo where id=:id")
    fun getTodo(id: Long): LiveData<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("Delete from Todo where id=:id")
    fun deleteTodo(id: Long)
}