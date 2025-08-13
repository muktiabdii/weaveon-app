package com.example.weaveon.data.model

import com.google.gson.annotations.SerializedName

data class SummarizationRequest(
    val inputs: String,
    val parameters: Parameters? = null
)

data class Parameters(
    @SerializedName("max_length")
    val maxLength: Int = 100,

    @SerializedName("min_length")
    val minLength: Int = 30,

    @SerializedName("do_sample")
    val doSample: Boolean = false
)
