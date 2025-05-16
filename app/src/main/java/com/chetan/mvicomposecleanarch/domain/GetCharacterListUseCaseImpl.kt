package com.chetan.mvicomposecleanarch.domain

import com.chetan.mvicomposecleanarch.base.common.ApiResponse
import com.chetan.mvicomposecleanarch.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCharacterListUseCaseImpl @Inject constructor(private val repository : CharacterRepository) :
    GetCharacterListUseCase {
    override suspend operator fun invoke() = flow{
        emit(ApiResponse.Loading())
        try {
            val response = repository.getCharacters()
            emit(ApiResponse.Success(response.results))
        }catch (e : Exception){
            emit(ApiResponse.Error(e.message,e))
        }
    }.catch {
        emit(ApiResponse.Error(it.message,it))
    }.flowOn(Dispatchers.IO)
}