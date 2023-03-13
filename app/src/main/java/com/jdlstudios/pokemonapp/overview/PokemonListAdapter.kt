package com.jdlstudios.pokemonapp.overview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdlstudios.pokemonapp.databinding.TextItemBinding
import com.jdlstudios.pokemonapp.network.Pokemon

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.TextItemViewHolder>() {

    var data = listOf<Pokemon>()
    /*set(value) {
        field = value
        notifyDataSetChanged()
    }*/

    class TextItemViewHolder private constructor(private val binding: TextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon) {
            binding.textName.text = item.name
            binding.textUrl.text = item.url
        }

        companion object {
            fun from(parent: ViewGroup): TextItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TextItemBinding.inflate(layoutInflater, parent, false)
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
        holder.bind(item)
    }
}