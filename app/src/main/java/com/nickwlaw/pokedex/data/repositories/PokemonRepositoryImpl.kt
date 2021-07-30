package com.nickwlaw.pokedex.data.repositories

import android.util.Log
import com.nickwlaw.pokedex.data.models.domain.Pokemon
import com.nickwlaw.pokedex.data.models.mappers.toDomain
import com.nickwlaw.pokedex.data.network.PokemonAPI
import kotlinx.coroutines.*

class PokemonRepositoryImpl(private val pokemonApi: PokemonAPI) : PokemonRepository {

    private val pokemonListCache = mutableListOf<Pokemon>()

    override fun getPokemonList(): List<Pokemon> {
        if (pokemonListCache.isEmpty()) {
            fetchPokemonList()
        }

        return pokemonListCache
    }

    private fun fetchPokemonList() {
        CoroutineScope(Dispatchers.IO).launch {
            val resourceListResponse = pokemonApi.getPokemonResourceList()

            if (resourceListResponse.isSuccessful) {
                Log.d(TAG, resourceListResponse.body().toString())

                resourceListResponse.body()?.let { response ->
                    val runningTasks = response.results.map {
                        async {
                            getPokemon(it.name)
                        }
                    }

                    val responses = runningTasks.awaitAll()
                    val pokemonList = mutableListOf<Pokemon>()

                    responses.sortedBy { it?.id }.forEach {
                        if (it != null) {
                            pokemonList.add(it)
                        }
                    }

                    pokemonListCache.addAll(pokemonList)
                }
            } else {
                Log.d(
                    TAG,
                    "Error fetching Pokemon Resource List: ${
                        resourceListResponse.errorBody().toString()
                    }"
                )
            }
        }
    }

    private suspend fun getPokemon(name: String): Pokemon? {
        val pokemonResponse = pokemonApi.getPokemon(name)

        return if (pokemonResponse.isSuccessful) {
            Log.d(TAG, pokemonResponse.body().toString())

            pokemonResponse.body()?.toDomain()
        } else {
            Log.d(TAG, "Error fetching Pokemon: ${pokemonResponse.errorBody().toString()}")
            null
        }
    }

    companion object {
        const val TAG = "PokemonRepo"
    }
}