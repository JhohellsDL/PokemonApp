package com.jdlstudios.pokemonapp.network

import com.squareup.moshi.Json

data class PokemonDetail(
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val location_area_encounters: String,
    //val moves: List<Move>,
    val name: String,
    val order: Int,
    val sprites: Sprites,
    val stats: List<Stats>,
    val weight: Int
)

data class Form(
    val name: String,
    val url: String
)

data class Sprites(
    val front_default: String,
    val other: Other
)

data class Other(
    val home: Home
)

data class Home(
    @Json(name = "front_default") val frontDefault: String,
    @Json(name = "front_female") val frontFemale: String?,
    @Json(name = "front_shiny") val frontShiny: String,
    @Json(name = "front_shiny_female") val frontShinyFemale: String?
)

data class Stats(
    @Json(name = "base_stat") val baseStat: Int,
    val effort: Int,
    val stat: Stat
)

data class Stat(
    val name: String,
    val url: String
)
