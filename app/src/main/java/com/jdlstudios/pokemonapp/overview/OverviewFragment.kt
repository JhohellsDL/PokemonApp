package com.jdlstudios.pokemonapp.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jdlstudios.pokemonapp.databinding.FragmentOverviewBinding
import com.jdlstudios.pokemonapp.databinding.GridViewItemBinding

class OverviewFragment : Fragment() {

    private lateinit var binding: GridViewItemBinding

    /*private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this)[OverviewViewModel::class.java]
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GridViewItemBinding.inflate(inflater)

        val viewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.property.observe(viewLifecycleOwner){
            Log.i("poke","poke!! ${it.name}")
        }
        binding.lifecycleOwner = this


        setHasOptionsMenu(true)
        return binding.root
    }
}