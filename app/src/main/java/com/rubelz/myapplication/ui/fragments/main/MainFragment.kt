package com.rubelz.myapplication.ui.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubelz.myapplication.R
import com.rubelz.myapplication.data.model.Todo
import com.rubelz.myapplication.databinding.FragmentMainBinding
import com.rubelz.myapplication.ui.adapters.TodoAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var bind: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentMainBinding.inflate(LayoutInflater.from(inflater.context), container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadTodos()

        bind.btnAddTodo.setOnClickListener {
            navigateTo()
        }
    }

    fun loadTodos() {
        val todo = Todo(1232, "Come Cow NOW", "DEscription of Cow")
        val todos = arrayListOf(todo)

        val adapter = TodoAdapter(todos)
        bind.rvTodos.layoutManager = LinearLayoutManager(requireContext())
        bind.rvTodos.adapter = adapter
    }

    fun navigateTo() {
        findNavController().navigate(R.id.detailsFragment)
    }

}