package repository
import repository.model.CountryResponse
import retrofit2.Response

class CountryRepository {
    suspend fun getCountryResponse() : Response<List<CountryResponse>> =
        CountryService.countryService.getCountriesResponse()

    suspend fun getCountryDetails(name: String) : Response<CountryResponse> =
        CountryService.countryService.getCountyDetails(name = name)

//    suspend fun getCountryDetails(name: String) : CountryResponse? =
//        CountryService.countryService.getCountyDetails(name = name).body()?.first()
}