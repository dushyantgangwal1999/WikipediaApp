package com.example.wikithree.model

import com.google.gson.annotations.SerializedName

data class MainDataClass(
    @SerializedName("batchcomplete")val batchcomplete:Boolean?,
    @SerializedName("continue") val continues:Continue?,
    @SerializedName("query")val query:Query?
)