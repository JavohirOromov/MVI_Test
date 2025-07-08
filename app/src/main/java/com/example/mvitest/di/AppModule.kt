package com.example.mvitest.di

import com.example.mvitest.data.remote.ApiHelper
import com.example.mvitest.data.remote.ApiHelperImpl
import com.example.mvitest.data.remote.ApiService
import com.example.mvitest.repository.MainRepository
import com.example.mvitest.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
    single<ApiHelper> { ApiHelperImpl(get()) }
    single { MainRepository(get()) }
    viewModel { DashboardViewModel(get()) }
}