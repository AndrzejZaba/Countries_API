package com.example.firstapp.ui.theme

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        //private suspend fun getCountriesResponse(): retrofit2.Response<CountryResponse> =  countryService.getCountries()
    //--- tu będzie koniec klasy CountryResponse

    private val mutableCountriesData = MutableLiveData<List<CountryResponse>>()
    val immutableCountriesData: LiveData<List<CountryResponse>> = mutableCountriesData

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val request = countryRepository.getCountryResponse()
                val countries = request.body()
                // W tym momencie w zmiennej countries mamy listę krajów pobranych z API
                // X
                mutableCountriesData.postValue(countries)

            }catch (e: Exception){
                Log.e("MainViewModel", "Operacja nie powiodla sie", e)
            }

        }
    }

}