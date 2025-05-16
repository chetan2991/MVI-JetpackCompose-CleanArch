package com.chetan.mvicomposecleanarch.data

import com.chetan.mvicomposecleanarch.data.model.CharacterResponse
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}