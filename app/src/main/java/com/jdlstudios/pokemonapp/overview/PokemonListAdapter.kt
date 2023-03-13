package com.jdlstudios.pokemonapp.overview

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdlstudios.pokemonapp.bindImage
import com.jdlstudios.pokemonapp.databinding.GridViewItemBinding
import com.jdlstudios.pokemonapp.network.Pokemon
import com.jdlstudios.pokemonapp.network.PokemonApi
import kotlinx.coroutines.*


class PokemonListAdapter(
    private val onClickListener: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonListAdapter.TextItemViewHolder>() {

    var data = listOf<Pokemon>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    class TextItemViewHolder private constructor(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        suspend fun bind(
            item: Pokemon,
            onClickListener: (Pokemon) -> Unit
        ) {
            binding.textNamePokemon.text = item.name
            val getPokemonDeferred = PokemonApi.retrofitService.getPropertiesAsync(item.name)
            val pokemonItem = getPokemonDeferred.await()
            bindImage(binding.pokemonImage, pokemonItem.sprites.other.home.frontDefault)
            itemView.setOnClickListener { onClickListener(item) }
        }

        companion object {
            fun from(parent: ViewGroup): TextItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridViewItemBinding.inflate(layoutInflater, parent, false)
                return TextItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        return TextItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        coroutineScope.launch {
            holder.bind(item, onClickListener)
        }
    }
}