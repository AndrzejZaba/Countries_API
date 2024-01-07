package com.example.firstapp.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.UiState
import com.example.firstapp.UiStateDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import repository.CountryRepository
import repository.model.Country
import repository.model.CountryResponse
import repository.model.Flags

class DetailsViewModel : ViewModel() {

    private val countryRepository = CountryRepository()

    private val mutableCountryDetails = MutableLiveData<UiStateDetails>()
    val immutableCountriesData: LiveData<UiStateDetails> = mutableCountryDetails


    fun loadDetailsData(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                mutableCountryDetails.postValue(UiStateDetails(isLoading = true))
                val request = countryRepository.getCountryDetailsResponse(name)

                if(request.isSuccessful) {
                    val countryRequest = request.body()?.first()

                    val details = countryRequest?.let {
                        CountryResponse(
                            name = it.name,
                            capital = it.capital,
                            flags = it.flags,
                            continents = it.continents
                        )
                    }

                    mutableCountryDetails.postValue(UiStateDetails(data = details!!, isLoading = false))


                }else{
                    Log.e("MainViewModel", "Request is not succesfull")
                }
            }catch (e: Exception){
                Log.e("MainViewModel", "Operacja nie powiodla sie - DetailsVM", e)
            }

        }

    }

}