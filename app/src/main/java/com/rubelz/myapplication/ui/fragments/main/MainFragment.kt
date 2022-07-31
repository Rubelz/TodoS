package com.rubelz.myapplication.ui.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubelz.myapplication.R
import com.rubelz.myapplication.databinding.FragmentMainBinding
import com.rubelz.myapplication.ui.TodoViewModel
import com.rubelz.myapplication.ui.adapters.TodoAdapter

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

        viewModel.getAllTodos().observe(viewLifecycleOwner) {
            Log.d("MyTag", it.toString())
            val adapter = TodoAdapter(it)
            bind?.rvTodos?.layoutManager = LinearLayoutManager(requireContext())
            bind?.rvTodos?.adapter = adapter
        }

        bind?.btnAddTodo?.setOnClickListener {
            navigateTo()
        }
    }

    fun navigateTo() {
        findNavController().navigate(R.id.detailsFragment)
    }

}