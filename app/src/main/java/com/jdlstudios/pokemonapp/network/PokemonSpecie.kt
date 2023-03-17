package com.jdlstudios.pokemonapp.network

data class PokemonSpecie(
    val color: Color,
    val egg_groups: List<EggGroups>,
    val evolves_from_species: EvolveFrom? = null,
    val genera: List<Genero>
)

data class Color(
    val name: String,
    val url: String
)

data class EggGroups(
    val name: String,
    val url: String
)

data class EvolveFrom(
    val name: String,
    val url: String
)

data class Genero(
    val genus: String,
    val language: Lenguaje
)

data class Lenguaje(
    val name: String,
    val url: String
)