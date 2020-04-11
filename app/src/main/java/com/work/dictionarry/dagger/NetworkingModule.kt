package com.work.dictionarry.dagger

import com.work.dictionarry.model.networking.retrofit.NetworkService
import com.work.dictionarry.model.networking.retrofit.WordsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun provideRetrofit() = NetworkService.retrofit


    @Singleton
    @Provides
    fun provideWordsApi(retrofit: Retrofit) = retrofit.create(WordsApi::class.java)
}