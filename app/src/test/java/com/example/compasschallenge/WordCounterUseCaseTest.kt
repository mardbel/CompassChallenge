package com.example.compasschallenge

import com.example.compasschallenge.flow.WordCounterUseCase
import com.example.compasschallenge.viewModels.TextRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.mockito.kotlin.mock
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class WordCounterUseCaseTest {
    @Test
    fun `when textRepository returns a string wordCounterUseCase generates a map with the right frequency `() {
        runBlocking  {
            val expected = mapOf(
                "like" to 1,
                "si" to 1,
                "estas" to 1,
                "revisando" to 1,
                "challenges" to 1,
                "hace" to 1,
                "horas" to 1
            )
            val textRepository = mock<TextRepository> {
                whenever(it.getCompassAboutText()).thenReturn("like si estas revisando challenges hace horas")
            }
            val wordCounterUseCase = WordCounterUseCase(textRepository)
            val result = wordCounterUseCase.invoke()

            assertEquals(expected, result)
        }
    }

    @Test
    fun `when textRepository returns a string with whitespaces wordCounterUseCase generates a map with the right frequency `() {
        runBlocking  {
            val expected = mapOf(
                "" to 2,
                "and" to 1,
                "subscribe" to 1,
                "to" to 1,
                "me" to 1,
                "as" to 1,
                "contractor" to 1,
            )
            val textRepository = mock<TextRepository> {
                whenever(it.getCompassAboutText()).thenReturn("and subscribe to me as contractor\n\n")
            }
            val wordCounterUseCase = WordCounterUseCase(textRepository)
            val result = wordCounterUseCase.invoke()

            assertEquals(expected, result)
        }
    }

}