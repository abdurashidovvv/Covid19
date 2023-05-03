package uz.abdurashidov.covid19.repository

import kotlinx.coroutines.flow.flow
import uz.abdurashidov.covid19.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getData() = flow { emit(apiService.getData()) }
}