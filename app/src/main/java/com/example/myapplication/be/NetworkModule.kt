package com.example.myapplication.be

import com.example.myapplication.usecase.GetVersionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.o2.sk/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideVersionRepository(apiService: ApiService): VersionRepository {
        return VersionRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideGetVersionUseCase(repository: VersionRepository): GetVersionUseCase {
        return GetVersionUseCase(repository)
    }
}