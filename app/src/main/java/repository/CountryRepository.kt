package repository
import repository.model.CountryResponse
import retrofit2.Response

class CountryRepository {
    suspend fun getCountryResponse() : Response<List<CountryResponse>> =
        CountryService.countryService.getCountriesResponse()

    suspend fun getCountryDetailsResponse(name: String) : Response<List<CountryResponse>> =
        CountryService.countryService.getCountyDetailsResponse(name = name)

//    suspend fun getCountryDetails(name: String) : CountryResponse? =
//        CountryService.countryService.getCountyDetails(name = name).body()?.first()
}