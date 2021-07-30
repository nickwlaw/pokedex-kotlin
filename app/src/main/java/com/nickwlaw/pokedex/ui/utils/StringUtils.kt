package com.nickwlaw.pokedex.ui.utils

import java.util.*

object StringUtils {
    fun String.toCapitalCase(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}