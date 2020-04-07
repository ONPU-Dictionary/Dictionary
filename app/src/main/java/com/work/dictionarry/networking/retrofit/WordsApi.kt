package com.work.dictionarry.networking.retrofit

import com.work.dictionarry.networking.Word
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WordsApi {

    @GET("words/{word}")
    fun getWord(@Path(value = "word") word: String): Call<Word>
}