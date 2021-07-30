package com.nickwlaw.pokedex.data.models.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<String>,
    val sprite: Sprite,
    val abilities: List<String>,
    val stats: List<Stat>
)

data class Sprite(
    val frontUrl: String,
    val backUrl: String,
    var displayUrl: String
)

data class Stat(
    val name: String,
    val value: Int
)