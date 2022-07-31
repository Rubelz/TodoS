package com.rubelz.myapplication.ui.fragments.details

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rubelz.myapplication.R
import com.rubelz.myapplication.data.model.Todo
import com.rubelz.myapplication.databinding.FragmentDetailsBinding
import com.rubelz.myapplication.ui.TodoViewModel

class DetailsFragment : Fragment() {

    private var bind: FragmentDetailsBinding? = null

    private val viewModel: TodoViewModel by viewModels()

    val todoArg by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind =
            FragmentDetailsBinding.inflate(
                LayoutInflater.from(inflater.context),
                container, false
            )

        // (activity as AppCompatActivity).setSupportActionBar(bind?.detailsToolbar)
        var todoId: Long? = -1
        viewModel.getTodo(todoArg.data).observe(viewLifecycleOwner, Observer {
            todoId = it?.id
            bind?.etTitle?.setText(it?.title ?: "")
            bind?.etDetails?.setText(it?.details ?: "")
        })

        bind?.btnDetailsBack?.setOnClickListener {
            saveTodo(todoId)
        }

        bind?.btnSaveTodo?.setOnClickListener {
            createDeleteDialog(todoId)
        }

        return bind?.root
    }

    private fun saveTodo(todoId: Long? = -1) {
        val title = bind?.etTitle?.text.toString()
        val details = bind?.etDetails?.text.toString()

        // -1 means that we are adding a new item
        if (todoArg.data != -1L) {
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

    private fun deleteTodo(id: Long) {

        if (id != -1L) {
            viewModel.deleteTodo(id)
        }

        findNavController().navigate(R.id.action_detailsFragment_to_mainFragment)
    }

    fun createDeleteDialog(todoId: Long?) {
        val dialog = object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> deleteTodo(todoId ?: -1)
                }
            }
        }

        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setMessage("Are you sure?").setPositiveButton("Yes", dialog)
            .setNegativeButton("Cancel", dialog).show()

    }

}