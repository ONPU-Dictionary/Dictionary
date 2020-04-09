package com.work.dictionarry.model.repository

import com.work.dictionarry.model.networking.models.RhymesWrapper
import com.work.dictionarry.model.networking.models.Synonyms
import com.work.dictionarry.model.networking.models.Word

interface WordsRepository {

    suspend fun findWord(word: String): ResponseState
    suspend fun findSynonyms(word: String): Synonyms?
    suspend fun findRhymes(word: String): RhymesWrapper?

    sealed class ResponseState {
        class ResponseError(): ResponseState()
        class ResponseSuccess(val word: Word?): ResponseState()
    }
}