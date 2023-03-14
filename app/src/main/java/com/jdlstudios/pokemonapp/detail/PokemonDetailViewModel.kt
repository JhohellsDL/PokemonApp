package com.jdlstudios.pokemonapp.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jdlstudios.pokemonapp.network.PokemonApi
import com.jdlstudios.pokemonapp.network.PokemonDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class PokemonDetailViewModel(name: String) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail>
        get() = _pokemonDetail

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getDetailPokemon(name)
    }

    private fun getDetailPokemon(name: String) {
        coroutineScope.launch {
            val getDetailDeferred = PokemonApi.retrofitService.getPropertiesAsync(name)
            try {
                val result = getDetailDeferred.await()
                _pokemonDetail.value = result
            }catch (e: Exception) {
                Log.i("poke", "${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}