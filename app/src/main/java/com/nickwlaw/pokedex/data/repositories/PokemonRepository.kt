package com.nickwlaw.pokedex.data.repositories

import com.nickwlaw.pokedex.data.models.domain.Pokemon

interface PokemonRepository {
    fun getPokemonList(): List<Pokemon>
}