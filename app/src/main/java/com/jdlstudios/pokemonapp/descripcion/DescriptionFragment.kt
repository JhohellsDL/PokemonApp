package com.jdlstudios.pokemonapp.descripcion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdlstudios.pokemonapp.R
import com.jdlstudios.pokemonapp.databinding.FragmentDescriptionBinding
import com.jdlstudios.pokemonapp.detail.PokemonDetailFragmentArgs
import com.jdlstudios.pokemonapp.overview.OverviewFragmentDirections

class DescriptionFragment : Fragment() {

    private lateinit var binding : FragmentDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDescriptionBinding.inflate(inflater)
        // Inflate the layout for this fragment

        return binding.root

    }

}