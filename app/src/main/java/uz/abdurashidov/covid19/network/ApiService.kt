package uz.abdurashidov.covid19.network

import retrofit2.Response
import retrofit2.http.GET
import uz.abdurashidov.covid19.models.covidmodels.ResponseCovidData
import uz.abdurashidov.covid19.models.covidnewsmodel.NewsResponse

interface ApiService {

    @GET("/")
    suspend fun getData(): Response<ResponseCovidData>


}