package com.jdlstudios.pokemonapp.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
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

        val adapter = PokemonListAdapter(onClickListener = {
            this.findNavController().navigate(
                OverviewFragmentDirections.actionOverviewFragment2ToPokemonDetailFragment(it.name)
            )
        })

        binding.pokemonList.adapter = adapter

        val manager = GridLayoutManager(activity, 3)
        binding.pokemonList.layoutManager = manager

        viewModel.listPokemon.observe(viewLifecycleOwner) {
            adapter.data = it!!
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
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}