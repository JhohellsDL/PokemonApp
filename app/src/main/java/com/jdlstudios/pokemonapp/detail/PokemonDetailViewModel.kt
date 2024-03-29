package com.jdlstudios.pokemonapp.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jdlstudios.pokemonapp.network.PokemonApi
import com.jdlstudios.pokemonapp.network.PokemonDetail
import com.jdlstudios.pokemonapp.network.PokemonSpecie
import com.jdlstudios.pokemonapp.network.Species
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.security.interfaces.EdECKey
import java.util.*

class PokemonDetailViewModel(
    name: String
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail>
        get() = _pokemonDetail

    private val _pokemonPrevDetail = MutableLiveData<PokemonDetail>()
    val pokemonPrevDetail: LiveData<PokemonDetail>
        get() = _pokemonPrevDetail

    private val _pokemonSpecie = MutableLiveData<PokemonSpecie>()
    val pokemonSpecie: LiveData<PokemonSpecie>
        get() = _pokemonSpecie

    private val _pokemonType = MutableLiveData<String>()
    val pokemonType: LiveData<String>
        get() = _pokemonType

    private val _pokemonGenero = MutableLiveData<String>()
    val pokemonGenero: LiveData<String>
        get() = _pokemonGenero

    private val _pokemonAltura = MutableLiveData<String>()
    val pokemonAltura: LiveData<String>
        get() = _pokemonAltura

    private val _pokemonPeso = MutableLiveData<String>()
    val pokemonPeso: LiveData<String>
        get() = _pokemonPeso

    private val _pokemonOrden = MutableLiveData<String>()
    val pokemonOrden: LiveData<String>
        get() = _pokemonOrden

    private val _pokemonName = MutableLiveData<String>()
    val pokemonName: LiveData<String>
        get() = _pokemonName

    private val _pokemonColor = MutableLiveData<String>()
    val pokemonColor: LiveData<String>
        get() = _pokemonColor

    private val _pokemonListType = MutableLiveData<List<String>>()
    val pokemonListType: LiveData<List<String>>
        get() = _pokemonListType

    private val _pokemonNameListState = MutableLiveData<List<String>>()
    val pokemonNameListState: LiveData<List<String>>
        get() = _pokemonNameListState

    private val _pokemonValorListState = MutableLiveData<List<String>>()
    val pokemonValorListState: LiveData<List<String>>
        get() = _pokemonValorListState

    private lateinit var namePreEvolve: String
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getDetailPokemon(name)
        getSpeciePokemon(name)
    }

    private fun getFromEvolved(name: String){
        coroutineScope.launch {
            val getFromEvolveDeferred = PokemonApi.retrofitService.getPropertiesAsync(name)
            try {
                val result = getFromEvolveDeferred.await()
                _pokemonPrevDetail.value = result
            }catch (e: Exception){
                Log.i("poke", "${e.message}")
            }
        }
    }

    private fun getDetailPokemon(name: String) {
        coroutineScope.launch {
            val getDetailDeferred = PokemonApi.retrofitService.getPropertiesAsync(name)
            try {
                val result = getDetailDeferred.await()
                val peso = result.weight / 2.205
                val altura = result.height / 5.94
                val alturaFormateado = String.format("%.2f", altura)
                val pesoFormateado = String.format("%.2f", peso)

                _pokemonDetail.value = result
                _pokemonType.value = result.types[0].type.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                _pokemonName.value = result.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    )else it.toString()
                }
                _pokemonAltura.value = "$alturaFormateado m"
                _pokemonPeso.value = "$pesoFormateado Kg"
                _pokemonOrden.value = result.order.toString()

                val listValorStats = mutableListOf<String>()
                val listNameStats = mutableListOf<String>()
                result.stats.forEach {
                    listValorStats.add(it.baseStat.toString())
                    listNameStats.add(it.stat.name)
                }
                _pokemonNameListState.value = listNameStats
                _pokemonValorListState.value = listValorStats

                val listTypes = mutableListOf<String>()
                result.types.forEach {
                    listTypes.add(it.type.name)
                }
                _pokemonListType.value = listTypes


            } catch (e: Exception) {
                Log.i("poke", "${e.message}")
            }
        }
    }

    private fun getSpeciePokemon(specie: String) {
        coroutineScope.launch {
            val getSpeciesDeferred = PokemonApi.retrofitService.getPokemonSpecieAsync(specie)
            try {
                val result = getSpeciesDeferred.await()
                _pokemonSpecie.value = result
                _pokemonColor.value = result.color.name
                namePreEvolve = result.evolves_from_species!!.name
                getFromEvolved(namePreEvolve)
                getGenero()
            } catch (e: Exception) {
                Log.i("poke", "${e.message}")
            }
        }
    }

    private fun getGenero() {
        _pokemonSpecie.value?.genera?.forEach {
            if (it.language.name == "es") {
                _pokemonGenero.value = it.genus
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}