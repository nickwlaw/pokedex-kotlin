package com.nickwlaw.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import com.nickwlaw.pokedex.viewmodel.PokemonListViewModel
import com.nickwlaw.pokedex.databinding.PokemonListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonListFragment : BaseFragment<PokemonListFragmentBinding>() {
    private val viewModel: PokemonListViewModel by viewModel()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> PokemonListFragmentBinding =
        PokemonListFragmentBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@PokemonListFragment.viewModel
            setVariable(BR.viewModel, viewModel)
        }

        viewModel.pokemonList.observe(viewLifecycleOwner, { viewModel.loadPokemon(it) })
    }

    companion object {
        const val TAG = "PokemonListFragment"
    }
}