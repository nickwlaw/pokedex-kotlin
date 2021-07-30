package com.nickwlaw.pokedex.di

import com.nickwlaw.pokedex.data.network.PokemonAPI
import com.nickwlaw.pokedex.data.repositories.ApiBuilder
import com.nickwlaw.pokedex.data.repositories.PokemonRepository
import com.nickwlaw.pokedex.data.repositories.PokemonRepositoryImpl
import com.nickwlaw.pokedex.viewmodel.PokemonListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { androidContext().resources }
    single { ApiBuilder.buildApi(PokemonAPI::class.java) }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    viewModel { PokemonListViewModel(get()) }
}