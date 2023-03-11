package com.jdlstudios.pokemonapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jdlstudios.pokemonapp.network.PokemonApi
import com.jdlstudios.pokemonapp.network.PokemonDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status


    private val _property = MutableLiveData<PokemonDetail>()
    val property: LiveData<PokemonDetail>
        get() = _property

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {

            val getPropertiesDeferred = PokemonApi.retrofitService.getPropertiesAsync("25")

            try {
                val result = getPropertiesDeferred.await()
                //var listPokemon = result.results
                _property.value = result
                _status.value = "Llamada exitosa : ${result.name}"
            }catch (e: Exception){
                _status.value = "Llamada fallida : " + e.message
            }
        }
        /*PokemonApi.retrofitService.getPropertiesAsync().enqueue( object : Callback<PokemonProperty> {
            override fun onFailure(call: Call<PokemonProperty>, t: Throwable) {
                _status.value = "Mal " + t.message
            }

            override fun onResponse(call: Call<PokemonProperty>, response: Response<PokemonProperty>) {
                _property.value = response.body()
            }
        })*/
    }

    /*private fun getPokemonProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonApi2.retrofitService2.getProperties2()
            try {
                var listResult = getPropertiesDeferred.await()
                Log.i("poke1","ok111 init $listResult[0]")
                _property.value = listResult
                Log.i("poke2","ok111 init $listResult[0]")
            }catch (e: Exception){
                _status.value = "Falló 2 ${e.message}"
                Log.i("poke","ok111 inierrrt ${e.message}")
            }
        }*/
    /* PokemonApi2.retrofitService2.getProperties2().enqueue(object : Callback<PokemonDetail> {
         override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
             //_status.value = "ok! 2: ${response.body()?.sprites?.back_default}"
             _property.value = response.body()
             Log.i("poke","ok111 ${response.body()}")
         }
         override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
             _status.value = "mal!! 2 "
             Log.i("poke","ok111 ${t.message}")
         }

     })*/
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}