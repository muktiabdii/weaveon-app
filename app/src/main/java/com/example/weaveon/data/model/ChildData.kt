package com.example.weaveon.data.model

data class ChildData(
    val name: String = "",
    val age: String = "",
    val gender: String = "",
    val answers: Map<String, List<String>> = emptyMap()
)
