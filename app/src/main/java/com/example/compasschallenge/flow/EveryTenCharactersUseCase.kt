package com.example.compasschallenge.flow

import com.example.compasschallenge.viewModels.TextRepository

class EveryTenCharactersUseCase(private val textRepository: TextRepository) {
    suspend operator fun invoke() : List<Char> {

        return try {
            val textFromUrl = textRepository.getCompassAboutText()
            val result = findTens(textFromUrl)
            result.filter { it != '\n' && !it.isWhitespace() }
        } catch (e: Exception) {
            e.printStackTrace()
            listOf(' ')
        }
    }
    private fun findTens(textFromUrl: String) : List<Char> {
        val extractedCharacters = mutableListOf<Char>()
        for (i in textFromUrl.indices step 10) {
            extractedCharacters.add(textFromUrl[i])
        }
        return extractedCharacters
    }
}