package uz.abdurashidov.covid19.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abdurashidov.covid19.network.ApiService
import uz.abdurashidov.covid19.utils.Constants
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("covidBaseUrl")
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Provides
    @Singleton
    @Named("covidRetrofit")
    fun provideRetrofit(@Named("covidBaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("covidApi")
    fun provideApiService(@Named("covidRetrofit") retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}