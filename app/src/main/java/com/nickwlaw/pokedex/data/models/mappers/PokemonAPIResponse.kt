package com.nickwlaw.pokedex.data.models.mappers

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResourceListResponse(
    val results: List<NamedAPIResource>
)

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val sprites: PokemonSprites,
    val abilities: List<PokemonAbility>,
    val stats: List<PokemonStat>
)

@JsonClass(generateAdapter = true)
data class PokemonAbility(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int,
    val ability: NamedAPIResource
)

@JsonClass(generateAdapter = true)
data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("back_default")
    val backDefault: String
)

@JsonClass(generateAdapter = true)
data class PokemonType(
    val slot: Int,
    val type: NamedAPIResource
)

@JsonClass(generateAdapter = true)
data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: NamedAPIResource
)

@JsonClass(generateAdapter = true)
data class NamedAPIResource(
    val name: String
)