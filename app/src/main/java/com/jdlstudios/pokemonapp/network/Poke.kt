package com.jdlstudios.pokemonapp.network

data class Poke(
    val sprites: SpriteInfo
)

data class SpriteInfo(
    val back_default: String?,
    val back_female: String?,
    val back_shiny: String?,
    val back_shiny_female: String?,
    val front_default: String?,
    val front_female: String?,
    val front_shiny: String?,
    val front_shiny_female: String?
)