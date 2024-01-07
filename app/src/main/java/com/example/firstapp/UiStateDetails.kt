package com.example.firstapp

import repository.model.Country
import repository.model.CountryResponse
import repository.model.Flags

data class UiStateDetails (
    val data: CountryResponse = CountryResponse(
        name = Country("",""),
        capital = null,
        flags = Flags(""),
        continents = null,
        population = 0,
        area = 0.0,
        region = "",
        subregion = ""

    ),
    val isLoading: Boolean = false,
    val error: String? = null
)