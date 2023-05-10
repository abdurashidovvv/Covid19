package uz.abdurashidov.covid19.news.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.abdurashidov.covid19.models.covidnewsmodel.NewsResponse


interface NewsNetwork {

    @GET("everything")
    suspend fun getCovidNews(
        @Query("q") covid: String = "covid",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "7c04fcfddd224ed6a591ac49e9abb8f2"
    ): Response<NewsResponse>
}