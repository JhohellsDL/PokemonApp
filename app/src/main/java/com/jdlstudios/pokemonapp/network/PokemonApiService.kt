package com.jdlstudios.pokemonapp.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

private const val REQUEST_TYPE  = "pokemon/{name}"
private const val REQUEST_SPECIES = "pokemon-species/{specie}"
private const val REQUEST_POKEMON_LIS = "pokemon?limit=100000&offset=0"

interface PokemonApiService {
    @GET(REQUEST_TYPE)
    fun getPropertiesAsync(@Path("name") name: String):
            Deferred<PokemonDetail>

    @GET(REQUEST_POKEMON_LIS)
    fun getListPokemonAsync():
            Deferred<PokemonList>

    @GET(REQUEST_SPECIES)
    fun getPokemonSpecieAsync(@Path("specie") specie: String):
            Deferred<PokemonSpecie>
}