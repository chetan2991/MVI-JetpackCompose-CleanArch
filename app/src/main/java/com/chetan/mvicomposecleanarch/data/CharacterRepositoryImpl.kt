package com.chetan.mvicomposecleanarch.data

import com.chetan.mvicomposecleanarch.data.model.CharacterResponse
import com.chetan.mvicomposecleanarch.domain.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val apiService: ApiService) : CharacterRepository {
    override suspend fun getCharacters(): CharacterResponse {
       return apiService.getCharacters()
    }
}