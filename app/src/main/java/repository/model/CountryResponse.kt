package repository.model

data class CountryResponse(
    val name: Country,
    val capital: List<String>?,
    val flags: Flags,
    val continents: List<String>?


)