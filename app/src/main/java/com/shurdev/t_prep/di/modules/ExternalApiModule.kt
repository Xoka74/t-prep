package com.shurdev.t_prep.di.modules

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExternalApiModule {
    @Provides
    @Singleton
    fun provideCsvReader() : CsvReader {
        return csvReader()
    }
}