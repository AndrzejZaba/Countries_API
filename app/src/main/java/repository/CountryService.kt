package repository

import repository.model.CountryResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface CountryService {

    //@Header("Authorization")

    @GET("/v3.1/all")
    //@GET("/v3.1/independent?status=true&fields=name,capital")

    suspend fun getCountriesResponse(): Response<List<CountryResponse>>

    @GET("/v3.1/name/{name}")
    //suspend fun getCountyDetails(@Path("name") name:String) : Response<CountryResponse>
    suspend fun getCountyDetailsResponse(@Path("name") name: String) : Response<List<CountryResponse>>


    companion object{
        private const val COUNTRY_URL = "https://restcountries.com/"

        val logger = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttp = OkHttpClient.Builder().apply {
            this.addInterceptor(logger)
                .readTimeout(30, TimeUnit.SECONDS)
        }.build()

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(COUNTRY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())
                //.client(okHttp)
                .build()
        }

        val countryService: CountryService by lazy{
            retrofit.create(CountryService::class.java)
        }
    }
}