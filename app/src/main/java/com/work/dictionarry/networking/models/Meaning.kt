package com.work.dictionarry.networking.models

import com.google.gson.annotations.SerializedName

data class Meaning (
    @SerializedName(value = "definition") val definition: String,
    @SerializedName(value = "partOfSpeech") val pathOfSpeech: String,
    @SerializedName(value = "examples") val examples: List<String>
)