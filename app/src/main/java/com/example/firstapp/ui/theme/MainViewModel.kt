package com.example.firstapp.ui.theme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.UiState
import repository.model.Country
import repository.model.CountryResponse
import repository.CountryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import repository.CountryRepository

class MainViewModel : ViewModel() {

    // to powinno być w warstwie repository, czyli w osobnej klasie o nazwie CountryRepository
    private val countryRepository = CountryRepository()

        //val countryService = CountryService.retrofit.create(CountryService::class.java)

    // suspend - operacja wejścia wyjścia związana z połączeniem sieciowym. Czyli będziemy musieli użyć kurtyny (to view model scope.launch)


    private val mutableCountriesData = MutableLiveData<UiState>()
    val immutableCountriesData: LiveData<UiState> = mutableCountriesData

    fun getData(){
        mutableCountriesData.postValue((UiState(isLoading = true)))
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val request = countryRepository.getCountryResponse()

                if(request.isSuccessful) {
                    val countries = request.body()
                    // W tym momencie w zmiennej countries mamy listę krajów pobranych z API
                    mutableCountriesData.postValue(UiState(countries = countries))
                } else{
                    mutableCountriesData.postValue((UiState(error = "Fail, code: ${request.code()}")))
                }
            }catch (e: Exception){
                mutableCountriesData.postValue(UiState(error = e.message) )
                Log.e("MainViewModel", "Operacja nie powiodla sie", e)
            }

        }
    }

}