package com.example.weaveon.domain.usecase

import com.example.weaveon.domain.repository.KidscoverRepository

class KidscoverUseCase(private val kidscoverRepository: KidscoverRepository) {

    suspend operator fun invoke(
        childName: String,
        age: String,
        gender: String,
        answers: Map<Int, List<String>>,
        onResult: (Boolean, String?) -> Unit
    ) {
        kidscoverRepository.saveChildData(childName, age, gender, answers, onResult)
    }
}
