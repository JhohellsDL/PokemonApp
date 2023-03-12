package com.jdlstudios.pokemonapp.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.jdlstudios.pokemonapp.R
import com.jdlstudios.pokemonapp.databinding.FragmentOverviewBinding

@Suppress("DEPRECATION")
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

        binding.buttonNext.setOnClickListener {
            it.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragment2ToPokemonDetailFragment(5))
        }

        setHasOptionsMenu(true)
        return binding.root
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}