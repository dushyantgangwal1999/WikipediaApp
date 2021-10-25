package com.example.wikithree.model

import com.google.gson.annotations.SerializedName
data class Page(
    @SerializedName("index")val index: Int?,
    @SerializedName("ns")val ns: Int?,
    @SerializedName("pageid")val pageid: Long?,
    @SerializedName("thumbnail")val thumbnail: Thumbnail?,
    @SerializedName("title")val title: String?,
    @SerializedName("terms")val terms:Terms?
)