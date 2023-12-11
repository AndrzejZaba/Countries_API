package repository
import repository.model.CountryResponse
import retrofit2.Response

class CountryRepository {
    suspend fun getCountryResponse() : Response<List<CountryResponse>> =
        CountryService.countryService.getCountriesResponse()
}