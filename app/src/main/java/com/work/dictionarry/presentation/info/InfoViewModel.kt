package com.work.dictionarry.presentation.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.dictionarry.model.networking.models.Word
import com.work.dictionarry.model.networking.retrofit.NetworkService
import com.work.dictionarry.model.repository.WordsRepository
import com.work.dictionarry.model.repository.WordsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoViewModel: ViewModel() {

    val loadingState: MutableLiveData<LoadingState> = MutableLiveData()
    private val wordsRepository: WordsRepository

    init {
        val wordsApi = NetworkService.instance().wordsApi
        wordsRepository =
            WordsRepositoryImpl(wordsApi)
    }

    fun getWordInfo(word: String) {
        loadingState.value = LoadingState.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            handleApiResponse(wordsRepository.findWord(word))
        }
    }

    private fun handleApiResponse(responseState: WordsRepository.ResponseState) {
        when(responseState) {
            is WordsRepository.ResponseState.ResponseError -> loadingState.postValue(LoadingState.Error())
            is WordsRepository.ResponseState.ResponseSuccess -> {
                loadingState.postValue(responseState.word?.let {LoadingState.Loaded(it)}?:LoadingState.Error())
            }
        }
    }

    sealed class LoadingState {
        class Loaded(val word: Word): LoadingState()
        class Loading(): LoadingState()
        class Error(): LoadingState()
    }
}