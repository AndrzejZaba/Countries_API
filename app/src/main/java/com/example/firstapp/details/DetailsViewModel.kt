package com.example.firstapp.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import repository.CountryRepository
import repository.model.CountryResponse

class DetailsViewModel : ViewModel() {

    private val countryRepository = CountryRepository()

    val liveData = MutableLiveData<CountryResponse>()


    fun loadDetailsData(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val request = countryRepository.getCountryDetails(name)

                if(request.isSuccessful) {
                    val country = request.body()
                    liveData.postValue(country)
                }
            }catch (e: Exception){
                Log.e("MainViewModel", "Operacja nie powiodla sie - DetailsVM", e)
            }

        }

    }

}