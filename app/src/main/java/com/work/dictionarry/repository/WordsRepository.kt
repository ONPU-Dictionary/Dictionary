package com.work.dictionarry.repository

import com.work.dictionarry.networking.Word

interface WordsRepository {

    suspend fun findWord(word: String): ResponseState

    sealed class ResponseState {
        class ResponseError(): ResponseState()
        class ResponseSuccess(val word: Word?): ResponseState()
    }
}