package com.example.compasschallenge.viewModels

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class TextRepository {

    suspend fun everyTenCharacterRequest(): List<String> {
        return withContext(Dispatchers.IO) {
            try {
                val document = Jsoup.connect("https://www.compass.com/about").get()
                val textWithLines: String = document.title()
                listOf(textWithLines)

            } catch (e: Exception) {
                e.printStackTrace()
                listOf("fallo request")
            }
        }
    }

    suspend fun wordCounterRequest(): List<String> {
        return listOf("hola11", "chau11")
    }

}
