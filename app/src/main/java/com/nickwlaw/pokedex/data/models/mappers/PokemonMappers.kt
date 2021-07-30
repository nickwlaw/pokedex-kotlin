package com.nickwlaw.pokedex.data.models.mappers

import com.nickwlaw.pokedex.data.models.domain.Pokemon
import com.nickwlaw.pokedex.data.models.domain.Sprite
import com.nickwlaw.pokedex.data.models.domain.Stat
import com.nickwlaw.pokedex.ui.utils.StringUtils.toCapitalCase

internal fun PokemonResponse.toDomain(): Pokemon {
    val typeList = mutableListOf<String>().apply {
        types.sortedBy { it.slot }.forEach { this.add(it.type.name) }
    }

    val sprite = Sprite(sprites.frontDefault, sprites.backDefault, sprites.frontDefault)

    val abilityList = mutableListOf<String>().apply {
        abilities.sortedBy { it.slot }.forEach {
            when {
                it.isHidden -> this.add(it.ability.name.plus(HIDDEN_STRING))
                else -> this.add(it.ability.name)
            }
        }
    }

    val statList = stats.map { Stat(it.stat.name, it.baseStat) }

    return Pokemon(
        id,
        name.toCapitalCase(),
        typeList,
        sprite,
        abilityList,
        statList
    )
}

const val HIDDEN_STRING = " (Hidden)"