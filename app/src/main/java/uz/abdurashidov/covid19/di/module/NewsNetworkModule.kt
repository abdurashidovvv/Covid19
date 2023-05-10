package uz.abdurashidov.covid19.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abdurashidov.covid19.news.network.NewsNetwork
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsNetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit):NewsNetwork=retrofit.create(NewsNetwork::class.java)
}