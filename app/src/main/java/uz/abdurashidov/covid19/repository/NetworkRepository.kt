package uz.abdurashidov.covid19.repository

import kotlinx.coroutines.flow.flow
import uz.abdurashidov.covid19.network.ApiService
import uz.abdurashidov.covid19.news.network.NewsNetwork
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsNetwork: NewsNetwork,
) {
    suspend fun getData() = flow { emit(apiService.getData()) }

    suspend fun getCovidNews() = flow { emit(newsNetwork.getCovidNews()) }
}