package com.example.myapplication.usecase

import com.example.myapplication.be.VersionRepository
import com.example.myapplication.be.VersionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetVersionUseCase@Inject constructor(
    private val repository: VersionRepository
){
    suspend operator fun invoke(code: String): VersionResponse {    // make as flow and add loading
        return repository.getVersion(code)
    }
}

//suspend operator fun invoke(code: String): Flow<VersionResponse> = flow {
//    // emit loading
//    emit(repository.getVersion(code))
//}.flowOn(Dispatchers.IO)