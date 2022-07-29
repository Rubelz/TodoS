package com.rubelz.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.rubelz.myapplication.data.dao.TodoDao
import com.rubelz.myapplication.data.model.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TodosRepository(val dao: TodoDao) {

    fun getAllTodos(): LiveData<List<Todo>> = dao.getAllTodos()

    fun getTodo(id: Long): LiveData<Todo> = dao.getTodo(id)

    fun addTodo(todo: Todo) {
        CoroutineScope(IO).launch {
            dao.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        CoroutineScope(IO).launch {
            dao.updateTodo(todo)
        }
    }

    fun deleteTodo(id: Long) {
        CoroutineScope(IO).launch {
            dao.deleteTodo(id)
        }
    }
}