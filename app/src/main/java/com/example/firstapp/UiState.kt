package com.example.firstapp

import repository.model.Country
import repository.model.CountryResponse

data class UiState (
    val countries: List<CountryResponse>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
    )