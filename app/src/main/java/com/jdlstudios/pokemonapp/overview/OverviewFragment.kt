package com.jdlstudios.pokemonapp.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.jdlstudios.pokemonapp.R
import com.jdlstudios.pokemonapp.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater)

        val viewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.property.observe(viewLifecycleOwner) {
            Log.i("poke", "poke!! ${it.name}")
        }

        binding.buttonNext.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_overviewFragment2_to_pokemonDetailFragment)
        )

        this.setHasOptionsMenu(true)
        return binding.root
    }
}