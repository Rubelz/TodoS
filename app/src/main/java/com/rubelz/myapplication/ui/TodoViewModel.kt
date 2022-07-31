package com.rubelz.myapplication.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rubelz.myapplication.data.database.TodosDb
import com.rubelz.myapplication.data.model.Todo
import com.rubelz.myapplication.data.repository.TodosRepository
import kotlinx.coroutines.launch

class TodoViewModel(app: Application) : AndroidViewModel(app) {

    private var repository: TodosRepository

    init {
        val dao = TodosDb.getDbInstance(app).todoDao()
        repository = TodosRepository(dao)
    }

    fun getAllTodos(): LiveData<List<Todo>> = repository.getAllTodos()

    fun getTodo(id: Long) = repository.getTodo(id)

    fun addTodo(todo: Todo) {
        repository.addTodo(todo)
    }

    fun deleteTodo(id: Long) {
        repository.deleteTodo(id)
    }

    fun updateTodo(todo: Todo) {
        repository.updateTodo(todo)
    }

}