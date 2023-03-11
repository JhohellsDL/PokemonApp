package com.jdlstudios.pokemonapp.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

private const val REQUEST_TYPE  = "pokemon/{name}"

interface PokemonApiService {
    @GET(REQUEST_TYPE)
    fun getPropertiesAsync(@Path("name") name: String):
            Deferred<PokemonDetail>
}