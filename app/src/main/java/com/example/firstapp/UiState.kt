package com.example.firstapp

import repository.model.Country

data class UiState (
    val countries: List<Country>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
    )