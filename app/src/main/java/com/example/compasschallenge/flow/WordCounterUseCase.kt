package com.example.compasschallenge.flow

import com.example.compasschallenge.viewModels.TextRepository

class WordCounterUseCase(private val textRepository: TextRepository) {
    suspend operator fun invoke() : Map<String, Int> {
        return try {
            val textFromUrl: String = textRepository.getCompassAboutText()
            val wordFrequencyMap = textFromUrl
                .splitToSequence(" ", "\n", ",")  //ver cual falto
                .fold(initial = mutableMapOf<String, Int>()) { map, text ->
                    val loweCaseText = text.lowercase()
                    val frequency = map.getOrDefault(loweCaseText, defaultValue = 0)
                    map[loweCaseText] = frequency + 1
                    map
                }
            val sortedWordFrequencyMap = wordFrequencyMap
                .toSortedMap(compareBy { it })
            sortedWordFrequencyMap
        } catch (e: Exception) {
            e.printStackTrace()
            mapOf()
        }
    }
}