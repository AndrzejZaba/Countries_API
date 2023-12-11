package repository.model

data class CountryResponse(
    val name: Country,
    val capital: Array<String> = emptyArray()
    //val flags: Flags,
    //val continent: Array<String> = emptyArray()


)