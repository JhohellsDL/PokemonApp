@file:Suppress("DEPRECATION")

package com.jdlstudios.pokemonapp.detail

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jdlstudios.pokemonapp.R
import com.jdlstudios.pokemonapp.colorMap
import com.jdlstudios.pokemonapp.colorPokemonType
import com.jdlstudios.pokemonapp.databinding.FragmentPokemonDetailBinding
import java.util.*

class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater)

        val args = PokemonDetailFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory = PokemonDetailViewModelFactory(args.name)

        val viewModel =
            ViewModelProvider(this, viewModelFactory)[PokemonDetailViewModel::class.java]
        binding.pokemonDetailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.pokemonColor.observe(viewLifecycleOwner) {
            val colorResId = colorMap[it] ?: R.color.new_white
            binding.constraintLayoutGeneral.setBackgroundResource(colorResId)
        }

        viewModel.pokemonListType.observe(viewLifecycleOwner) {
            val colorFirstType = colorPokemonType[it[0]] ?: R.color.new_white
            binding.cardPokemonType1.setBackgroundResource(colorFirstType)
            binding.textFirstType.text = it[0].capitalize(Locale.getDefault())
            if (it.size > 1) {
                val colorSecondType = colorPokemonType[it[1]] ?: R.color.new_white
                binding.cardPokemonType2.setBackgroundResource(colorSecondType)
                binding.cardViewSecondType.isGone = false
                binding.textSecondType.text = it[1].capitalize(Locale.getDefault())
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareIntent(): Intent {
        //val args = PokemonDetailFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, "adfasdf")
        return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)

        if (getShareIntent().resolveActivity(requireActivity().packageManager) == null) {
            menu.findItem(R.id.share).isVisible = false
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}