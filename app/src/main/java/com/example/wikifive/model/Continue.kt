package com.example.wikithree.model

import com.google.gson.annotations.SerializedName

data class Continue(
    @SerializedName("continue")val continues: String?,
    @SerializedName("gpsoffset")val gpsoffset: Int?
)