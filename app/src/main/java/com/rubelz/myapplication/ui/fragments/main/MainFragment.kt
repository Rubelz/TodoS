package com.rubelz.myapplication.ui.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubelz.myapplication.R
import com.rubelz.myapplication.data.model.Todo
import com.rubelz.myapplication.databinding.FragmentMainBinding
import com.rubelz.myapplication.ui.TodoViewModel
import com.rubelz.myapplication.ui.adapters.TodoAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainFragment : Fragment() {

    private val viewModel: TodoViewModel by viewModels()

    private var bind: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = FragmentMainBinding.inflate(LayoutInflater.from(inflater.context), container, false)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind?.rvTodos?.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllTodos().observe(viewLifecycleOwner, Observer {

        val adapter = TodoAdapter(it)
        bind?.rvTodos?.adapter = adapter
        })

        bind?.btnAddTodo?.setOnClickListener {
            navigateTo()
        }
    }

    fun navigateTo() {
        findNavController().navigate(R.id.detailsFragment)
    }

}