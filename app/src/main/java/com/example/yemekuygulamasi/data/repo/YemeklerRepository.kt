package com.example.yemekuygulamasi.data.repo

import com.example.yemekuygulamasi.data.datasource.YemeklerDataSource
import com.example.yemekuygulamasi.data.endity.SepetYemekler
import com.example.yemekuygulamasi.data.endity.Yemekler

class YemeklerRepository(var yRepo: YemeklerDataSource) {
    suspend fun yemekleriYukle(): List<Yemekler> = yRepo.yemekleriYukle()

    suspend fun sepeteYemekleriEkle(
        sepet_yemek_id: Int,
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) = yRepo.yemekleriEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    suspend fun sepettekiYemekleriYukle(): List<SepetYemekler>? = yRepo.sepettekiYemekleriYukle()

    suspend fun yemekSil(sepet_yemek_id: Int,kullanici_adi: String) = yRepo.yemekSil(sepet_yemek_id,kullanici_adi)
}