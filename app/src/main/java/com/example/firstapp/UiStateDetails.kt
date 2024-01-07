package com.example.firstapp

import repository.model.Country
import repository.model.CountryResponse
import repository.model.Flags

data class UiStateDetails (
    val data: CountryResponse = CountryResponse(
        name = Country("",""),
        capital = null,
        flags = Flags(""),
        continents = null
    ),
    val isLoading: Boolean = false,
    val error: String? = null
)