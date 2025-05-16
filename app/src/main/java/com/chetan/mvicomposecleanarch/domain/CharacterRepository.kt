package com.chetan.mvicomposecleanarch.domain

import com.chetan.mvicomposecleanarch.data.model.CharacterResponse

interface CharacterRepository {
    suspend fun getCharacters(): CharacterResponse
}