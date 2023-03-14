package com.jdlstudios.pokemonapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PokemonDetailViewModelFactory(private val name: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}