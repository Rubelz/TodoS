package com.rubelz.myapplication.ui.fragments.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rubelz.myapplication.R
import com.rubelz.myapplication.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var bind: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind =
            FragmentDetailsBinding.inflate(LayoutInflater.from(inflater.context), container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        
    }
}