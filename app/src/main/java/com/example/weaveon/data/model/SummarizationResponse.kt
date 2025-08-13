package com.example.weaveon.data.model

import com.google.gson.annotations.SerializedName

data class SummarizationResponse(
    @SerializedName("summary_text")
    val summaryText: String? = null
)
