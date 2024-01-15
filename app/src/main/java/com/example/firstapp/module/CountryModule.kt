package com.example.firstapp.module

import com.example.firstapp.details.DetailsViewModel
import com.example.firstapp.ui.theme.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import repository.CountryRepository

val countryModule = module {
    single {CountryRepository()}

    viewModel{ MainViewModel(get())}
    viewModel{ DetailsViewModel(get())}
}