package com.example.myapplication.be

interface VersionRepository {
    suspend fun getVersion(code: String): VersionResponse
}


class VersionRepositoryImpl(private val apiService: ApiService) : VersionRepository {
    override suspend fun getVersion(code: String): VersionResponse {
        return apiService.getVersion(code)
    }
}