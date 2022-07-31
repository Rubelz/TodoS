package com.rubelz.myapplication.ui.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rubelz.myapplication.R
import com.rubelz.myapplication.data.model.Todo
import com.rubelz.myapplication.databinding.FragmentDetailsBinding
import com.rubelz.myapplication.ui.TodoViewModel
import java.util.*

class DetailsFragment : Fragment() {

    val todoArg by navArgs<DetailsFragmentArgs>()

    private val viewModel: TodoViewModel by viewModels()

    private var bind: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind =
            FragmentDetailsBinding.inflate(
                LayoutInflater.from(inflater.context),
                container, false
            )

        var todoId: Long? = -1
        viewModel.getTodo(todoArg.data).observe(viewLifecycleOwner, Observer {
            todoId = it?.id
            bind?.etTitle?.setText(it?.title ?: "")
            bind?.etDetails?.setText(it?.details ?: "")
        })

        bind?.btnSaveTodo?.setOnClickListener {
            saveTodo(todoId)
        }

        return bind?.root
    }


    private fun saveTodo(todoId: Long? = -1){
        val title = bind?.etTitle?.text.toString()
        val details = bind?.etDetails?.text.toString()

        if (todoArg.data != -1L ) {
            addTodo(todoId, title, details)
        } else {
            addTodo(title, details)
        }
    }

    private fun addTodo(title: String, details: String) {
        val todo = Todo(title = title, details = details)

        viewModel.addTodo(todo)
        findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
    }

    private fun addTodo(id: Long?, title: String, details: String) {
        val todo = Todo(id, title = title, details = details)

        viewModel.updateTodo(todo)
        findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
    }

}