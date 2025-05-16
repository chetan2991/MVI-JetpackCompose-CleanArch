package com.chetan.mvicomposecleanarch.domain

import com.chetan.mvicomposecleanarch.base.common.ApiResponse
import com.chetan.mvicomposecleanarch.data.model.CharacterResponse
import com.chetan.mvicomposecleanarch.data.model.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.io.IOException

@ExperimentalCoroutinesApi
class GetCharacterListUseCaseImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: CharacterRepository
    private lateinit var useCase: GetCharacterListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        useCase = GetCharacterListUseCaseImpl(repository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke when repository return success`() = runTest(testDispatcher) {
        //Arrange
        val mockCharacter1 =
            Result("", listOf(), "", 1, "", mockk(), "character1", mockk(), "", "", "", "")
        val mockCharacter2 =
            Result("", listOf(), "", 2, "", mockk(), "character2", mockk(), "", "", "", "")
        val initialResults = listOf(mockCharacter1, mockCharacter2)
        val mockResponse = CharacterResponse(results = initialResults, info = mockk())

        coEvery { repository.getCharacters() } returns mockResponse

        //Act
        val emmission = useCase().toList()

        //Assert
        assertEquals(2, emmission.size)
        assertTrue(emmission[0] is ApiResponse.Loading, "first emit Loding")
        val response = emmission[1] as ApiResponse.Success<List<Result>>
        assertEquals(mockCharacter1, response.data?.get(0))
        assertEquals(mockCharacter2, response.data?.get(1))
        coVerify(exactly = 1) { repository.getCharacters() }

    }

    @Test
    fun `invoke when repository throw exception`() = runTest(testDispatcher) {
        //Arrange
        val errorMessage = "Network error"
        val ioException = IOException(errorMessage)
        coEvery { repository.getCharacters() } throws ioException
        //Act
        val emmissions = useCase().toList()

        //Assert
        assertEquals(2, emmissions.size)
        assertTrue(emmissions[0] is ApiResponse.Loading, "first emit Loding")
        val response = emmissions[1] as ApiResponse.Error
        assertEquals(errorMessage, response.message)
        coVerify(exactly = 1) { repository.getCharacters() }
    }

}