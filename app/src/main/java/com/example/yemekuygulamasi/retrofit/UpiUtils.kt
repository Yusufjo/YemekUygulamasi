package com.example.yemekuygulamasi.retrofit

class UpiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemeklerDao():YemeklerApi{
            return RetrofitClient.getClient(BASE_URL).create(YemeklerApi::class.java)
        }
    }
}