package com.example.yemekuygulamasi.data.datasource

import com.example.yemekuygulamasi.data.endity.SepetYemekler
import com.example.yemekuygulamasi.data.endity.Yemekler
import com.example.yemekuygulamasi.retrofit.YemeklerApi
import com.example.yemekuygulamasi.store.C
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class YemeklerDataSource(var ydao:YemeklerApi) {
    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO) {
        return@withContext ydao.YemekleriYukle().yemekler
    }

    suspend fun yemekleriEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ): String = withContext(Dispatchers.IO) {
        return@withContext ydao.sepeteYemekEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        ).success
    }

    suspend fun sepettekiYemekleriYukle(): List<SepetYemekler>? = withContext(Dispatchers.IO) {
        return@withContext try {
            ydao.sepettekiYemekleriYukle(C.kullanici_adi)?.sepet_yemekler
        } catch (e: Exception) {
            null
        }
    }

    suspend fun yemekSil(sepet_yemek_id: Int, kullanici_adi: String) = withContext(Dispatchers.IO) {
        ydao.yemeksil(sepet_yemek_id, kullanici_adi)
    }
}