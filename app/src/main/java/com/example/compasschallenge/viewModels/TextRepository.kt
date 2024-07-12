package com.example.compasschallenge.viewModels

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class TextRepository(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private var cachedTextEveryTen: String = ""
    suspend fun getCompassAboutText(): String {
        return withContext(dispatcher) {
        if (cachedTextEveryTen.isNotEmpty()) return@withContext cachedTextEveryTen
        try {
            val document = Jsoup.connect("https://www.compass.com/about").get()
            val textFromUrl: String = Jsoup.parse(document.html()).wholeText()
            cachedTextEveryTen = textFromUrl
            textFromUrl
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
        }
    }
}
