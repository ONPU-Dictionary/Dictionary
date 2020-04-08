package com.work.dictionarry.repository

import com.work.dictionarry.networking.models.Word
import com.work.dictionarry.networking.retrofit.WordsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.work.dictionarry.repository.WordsRepository.ResponseState

class WordsRepositoryImpl(private val wordsApi: WordsApi): WordsRepository {

    override suspend fun findWord(word: String): ResponseState = suspendCoroutine{
        wordsApi.getWord(word).enqueue(object : Callback<Word> {
            override fun onFailure(call: Call<Word>, t: Throwable) {
                t.printStackTrace()
                it.resume(ResponseState.ResponseError())
            }

            override fun onResponse(call: Call<Word>, response: Response<Word>) {
                it.resume(ResponseState.ResponseSuccess(response.body()))
            }
        })
    }
}