package com.nickwlaw.pokedex.data.network

import com.nickwlaw.pokedex.data.models.mappers.PokemonResourceListResponse
import com.nickwlaw.pokedex.data.models.mappers.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    @GET("pokemon?limit=151")
    suspend fun getPokemonResourceList(): Response<PokemonResourceListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") pokemonName: String): Response<PokemonResponse>
}