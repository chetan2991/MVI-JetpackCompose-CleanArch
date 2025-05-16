package com.chetan.mvicomposecleanarch.domain

import com.chetan.mvicomposecleanarch.base.common.ApiResponse
import com.chetan.mvicomposecleanarch.data.model.Result
import kotlinx.coroutines.flow.Flow

interface GetCharacterListUseCase {
    suspend operator fun invoke() : Flow<ApiResponse<List<Result>>>
}