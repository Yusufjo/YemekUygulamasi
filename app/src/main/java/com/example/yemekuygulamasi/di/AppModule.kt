package com.example.yemekuygulamasi.di

import com.example.yemekuygulamasi.data.datasource.YemeklerDataSource
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.example.yemekuygulamasi.retrofit.YemeklerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerRepository(yRepo: YemeklerDataSource): YemeklerRepository {
        return YemeklerRepository(yRepo)
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao: YemeklerApi): YemeklerDataSource {
        return YemeklerDataSource(ydao)
    }

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("http://kasimadalan.pe.hu/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun provideYemeklerDao(retrofit: Retrofit):YemeklerApi{
        return retrofit.create(YemeklerApi::class.java)
    }
}