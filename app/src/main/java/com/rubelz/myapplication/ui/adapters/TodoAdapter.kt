package com.rubelz.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rubelz.myapplication.data.model.Todo
import com.rubelz.myapplication.databinding.ItemTodoBinding
import com.rubelz.myapplication.ui.fragments.details.DetailsFragmentDirections
import com.rubelz.myapplication.ui.fragments.main.MainFragmentDirections

class TodoAdapter(private val todoList: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        return TodoHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind.tvTodoTitle.text = todoList[position].title

        holder.bind.root.setOnClickListener {
            val data =
                MainFragmentDirections.actionMainFragmentToDetailsFragment(
                    todoList[position].id ?: 0)
            Navigation.findNavController(it).navigate(data)
        }
    }

    override fun getItemCount(): Int = todoList.size


    class TodoHolder(val bind: ItemTodoBinding) : RecyclerView.ViewHolder(bind.root)
}