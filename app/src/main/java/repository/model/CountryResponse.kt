package repository.model

data class CountryResponse(
    val name: Country,
    val capital: List<String>?,
    val flags: Flags,
    val continents: List<String>?,
    val population: Int,
    val area: Double,
    val region: String,
    val subregion: String


)