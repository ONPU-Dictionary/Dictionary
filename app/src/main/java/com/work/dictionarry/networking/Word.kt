package com.work.dictionarry.networking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName(value = "word") val word: String,
    @SerializedName(value = "results") val meanings: List<Meaning>,
    @SerializedName(value = "syllables") val syllables: Syllables,
    @SerializedName(value = "pronunciation") val pronunciation: Pronunciation,
    @SerializedName(value = "frequency") val frequency: Float
)