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
            binding.constraintLayoutStats.setBackgroundResource(colorResId)
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

        viewModel.pokemonValorListState.observe(viewLifecycleOwner){
            binding.progressStat1.progress = it[0].toInt()/2
            binding.progressStat2.progress = it[1].toInt()/2
            binding.progressStat3.progress = it[2].toInt()/2
            binding.progressStat4.progress = it[3].toInt()/2
            binding.progressStat5.progress = it[4].toInt()/2
            binding.progressStat6.progress = it[5].toInt()/2

            binding.textValorStat1.text = it[0]
            binding.textValorStat2.text = it[1]
            binding.textValorStat3.text = it[2]
            binding.textValorStat4.text = it[3]
            binding.textValorStat5.text = it[4]
            binding.textValorStat6.text = it[5]
        }
        viewModel.pokemonNameListState.observe(viewLifecycleOwner){
            binding.textStat1.text = it[0].capitalize(Locale.getDefault())
            binding.textStat2.text = it[1].capitalize(Locale.getDefault())
            binding.textStat3.text = it[2].capitalize(Locale.getDefault())
            binding.textStat4.text = it[3].capitalize(Locale.getDefault())
            binding.textStat5.text = it[4].capitalize(Locale.getDefault())
            binding.textStat6.text = it[5].capitalize(Locale.getDefault())
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