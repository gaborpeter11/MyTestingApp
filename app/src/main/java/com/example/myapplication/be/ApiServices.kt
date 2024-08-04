package com.example.myapplication.be

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("version")
    suspend fun getVersion(@Query("code") code: String): VersionResponse
}