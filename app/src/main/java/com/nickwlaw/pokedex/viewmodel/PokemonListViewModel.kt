package com.nickwlaw.pokedex.viewmodel

import androidx.lifecycle.*
import com.nickwlaw.pokedex.data.models.domain.Pokemon
import com.nickwlaw.pokedex.data.repositories.PokemonRepository
import com.nickwlaw.pokedex.ui.adapters.PokemonAdapter

class PokemonListViewModel(
    private val pokeRepo: PokemonRepository
) : ViewModel() {
    var adapter = PokemonAdapter(emptyList())

    val pokemonList: LiveData<List<Pokemon>> = liveData { emit(pokeRepo.getPokemonList()) }

    fun loadPokemon(pokemonList: List<Pokemon>) {
        adapter.setItems(pokemonList)
    }
}