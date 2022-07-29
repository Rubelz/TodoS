package com.rubelz.myapplication.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rubelz.myapplication.data.model.Todo
import com.rubelz.myapplication.databinding.FragmentDetailsBinding
import com.rubelz.myapplication.ui.TodoViewModel

class DetailsFragment : Fragment() {

    private val viewModel: TodoViewModel by viewModels()

    private var bind: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind =
            FragmentDetailsBinding.inflate(LayoutInflater.from(inflater.context), container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind?.btnSaveTodo?.setOnClickListener {
            addTodo()
        }
    }

    fun addTodo() {
        val title = bind?.tvTitle?.text.toString()
        val details = bind?.tvDetails?.text.toString()

        val todo = Todo(title = title, details = details)

        viewModel.addTodo(todo)
    }
}