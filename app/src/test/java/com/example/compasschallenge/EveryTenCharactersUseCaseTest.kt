package com.example.compasschallenge

import com.example.compasschallenge.flow.EveryTenCharactersUseCase
import com.example.compasschallenge.flow.WordCounterUseCase
import com.example.compasschallenge.viewModels.TextRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class EveryTenCharactersUseCaseTest {

    @Test
    fun `when textRepository returns a string EveryTenCharactersUseCase generates a list of every ten characters `() {
        runBlocking  {
            val expected = listOf('l','t','n','n','h')
            val textRepository = mock<TextRepository> {
                whenever(it.getCompassAboutText()).thenReturn("like si estas revisando challenges hace horas")
            }
            val everyTenCharactersUse = EveryTenCharactersUseCase(textRepository)
            val result = everyTenCharactersUse.invoke()

            TestCase.assertEquals(expected, result)
        }
    }

    @Test
    fun `when textRepository returns a empty string EveryTenCharactersUseCase generates a empty list of characters `() {
        runBlocking  {
            val expected = emptyList<Char>()
            val textRepository = mock<TextRepository> {
                whenever(it.getCompassAboutText()).thenReturn("")
            }
            val everyTenCharactersUse = EveryTenCharactersUseCase(textRepository)
            val result = everyTenCharactersUse.invoke()

            TestCase.assertEquals(expected, result)
        }
    }

}