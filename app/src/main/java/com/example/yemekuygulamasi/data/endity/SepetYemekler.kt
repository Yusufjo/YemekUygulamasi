package com.example.yemekuygulamasi.data.endity

import com.example.yemekuygulamasi.store.C
import java.io.Serializable

class SepetYemekler(var sepet_yemek_id:Int,
                    var yemek_adi:String,
                    var yemek_resim_adi:String,
                    var yemek_fiyat:Int,
                    var yemek_siparis_adet:Int,
                    var kullanici_adi:String):Serializable {
    companion object {
        fun from(yemek: Yemekler): SepetYemekler {
            return SepetYemekler(
                yemek.yemek_id,
                yemek.yemek_adi,
                yemek.yemek_resim_adi,
                yemek.yemek_fiyat,
                1, C.kullanici_adi
            )
        }
    }
}