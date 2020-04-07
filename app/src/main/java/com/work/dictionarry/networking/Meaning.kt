package com.work.dictionarry.networking

import com.google.gson.annotations.SerializedName

data class Meaning (
    @SerializedName(value = "definition") val definition: String,
    @SerializedName(value = "partOfSpeech") val pathOfSpeech: String,
    @SerializedName(value = "synonyms") val synonyms: List<String>,
    @SerializedName(value = "derivation") val derivation: List<String>,
    @SerializedName(value = "examples") val examples: List<String>
)