package com.chetan.mvicomposecleanarch.domain.di

import com.chetan.mvicomposecleanarch.domain.GetCharacterListUseCaseImpl
import com.chetan.mvicomposecleanarch.domain.GetCharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetCharacterListUseCase(getCharacterListUseCaseImpl: GetCharacterListUseCaseImpl): GetCharacterListUseCase {
        return getCharacterListUseCaseImpl
    }
}