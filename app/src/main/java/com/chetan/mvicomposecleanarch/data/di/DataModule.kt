package com.chetan.mvicomposecleanarch.data.di

import com.chetan.mvicomposecleanarch.data.CharacterRepositoryImpl
import com.chetan.mvicomposecleanarch.domain.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCharactorRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository {
        return characterRepositoryImpl
    }
}