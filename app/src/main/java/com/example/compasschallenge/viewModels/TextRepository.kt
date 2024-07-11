package com.example.compasschallenge.viewModels

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class TextRepository {

    suspend fun everyTenCharacterRequest(): List<Char> {
        return withContext(Dispatchers.IO) {
            try {
                val document = Jsoup.connect("https://www.compass.com/about").get()
                val textFromUrl: String = Jsoup.parse(document.html()).wholeText()
                val result = findTens(textFromUrl)
                result

            } catch (e: Exception) {
                e.printStackTrace()
                listOf('f')
            }
        }
    }

    suspend fun wordCounterRequest(): Map<String, Int>  {
        return withContext(Dispatchers.IO) {
            try {
                val document = Jsoup.connect("https://www.compass.com/about").get()
                val textFromUrl: String = Jsoup.parse(document.html()).text()
                val wordList = textFromUrl.split(" ")
                val wordFrequencyMap = wordList
                    .groupBy { it }
                    .mapValues { it.value.size } // Count occurrences of each word
                wordFrequencyMap

            } catch (e: Exception) {
                e.printStackTrace()
                mapOf("f" to 1)
            }
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
