package com.work.dictionarry.networking.models

import com.google.gson.annotations.SerializedName

data class Pronunciation(
    @SerializedName(value = "all") val pronunciation: String
)