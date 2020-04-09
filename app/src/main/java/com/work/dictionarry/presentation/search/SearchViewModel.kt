package com.work.dictionarry.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.dictionarry.model.networking.models.Word
import com.work.dictionarry.model.networking.retrofit.NetworkService
import com.work.dictionarry.model.repository.WordsRepository
import com.work.dictionarry.model.repository.WordsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel() : ViewModel() {

    private val _wordLd: MutableLiveData<DataLoadingProgress> = MutableLiveData()
    val wordLd: LiveData<DataLoadingProgress> = _wordLd

    var wordsRepository: WordsRepository

    init {
        val wordsApi = NetworkService.instance().wordsApi
        wordsRepository =
            WordsRepositoryImpl(wordsApi)
    }

    fun findWord(word: String) {
        _wordLd.value = DataLoadingProgress.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            handleResponse(wordsRepository.findWord(word))
        }
    }

    private fun handleResponse(responseState: WordsRepository.ResponseState) {
        when (responseState) {
            is WordsRepository.ResponseState.ResponseError -> _wordLd.postValue(DataLoadingProgress.Error())
            is WordsRepository.ResponseState.ResponseSuccess -> {
                val words = responseState.word
                val list = words?.let { listOf(it)}?: emptyList()
                _wordLd.postValue(DataLoadingProgress.Loaded(list))
            }
        }
    }

    sealed class DataLoadingProgress {
        class Loading(): DataLoadingProgress()
        class Error(): DataLoadingProgress()
        class Loaded(val words: List<Word>): DataLoadingProgress()
    }
}