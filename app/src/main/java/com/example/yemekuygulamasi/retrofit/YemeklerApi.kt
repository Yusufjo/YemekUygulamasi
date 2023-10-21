package com.example.yemekuygulamasi.retrofit

import com.example.yemekuygulamasi.data.endity.SepetYemeklerCevap
import com.example.yemekuygulamasi.data.endity.YemeklerCRUDCevap
import com.example.yemekuygulamasi.data.endity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface YemeklerApi {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun YemekleriYukle() : YemeklerCevap

//    @POST("yemekler/sepeteYemekEkle.php")
//    @FormUrlEncoded
//    suspend fun sepeteYemekEkle(@Field("yemek_adi") yemek_adi: String,
//                                @Field("yemek_resim_adi") yemek_resim_adi: String,
//                                @Field("yemek_fiyat") yemek_fiyat: Int,
//                                @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
//                                @Field("kullanici_adi")  kullanici_adi: String): YemeklerCRUDCevap
@POST("yemekler/sepeteYemekEkle.php") // -> CREATE
@FormUrlEncoded
suspend fun sepeteYemekEkle(@Field("yemek_adi") yemek_adi: String,
                            @Field("yemek_resim_adi") yemek_resim_adi: String,
                            @Field("yemek_fiyat") yemek_fiyat: Int,
                            @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                            @Field("kullanici_adi")  kullanici_adi: String): YemeklerCRUDCevap


@POST("yemekler/sepettekiYemekleriGetir.php")
@FormUrlEncoded
suspend fun sepettekiYemekleriYukle(@Field("kullanici_adi")  kullanici_adi: String) : SepetYemeklerCevap

@POST("yemekler/sepettenYemekSil.php")
@FormUrlEncoded
suspend fun yemeksil(@Field("sepet_yemek_id") sepet_yemek_id:Int,@Field("kullanici_adi")  kullanici_adi: String) : YemeklerCRUDCevap
}