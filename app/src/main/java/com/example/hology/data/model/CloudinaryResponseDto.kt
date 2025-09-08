package com.example.hology.data.model

import com.google.gson.annotations.SerializedName

data class CloudinaryResponseDto(
    val url: String,
    @SerializedName("secure_url") val secureUrl: String)