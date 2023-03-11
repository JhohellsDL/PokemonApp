package com.jdlstudios.pokemonapp.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jdlstudios.pokemonapp.R
import com.jdlstudios.pokemonapp.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

}