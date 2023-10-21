package com.example.yemekuygulamasi.store

import com.example.yemekuygulamasi.data.endity.SepetYemekler
import com.example.yemekuygulamasi.data.endity.Yemekler
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Sepet {object Sepet { // sepet verisini tutar
    private val list = mutableListOf<SepetYemekler>()
    private var yemeklerSunucudanAlindi = false

    fun sepetiYukle(sepet: List<SepetYemekler>) {
        if (!yemeklerSunucudanAlindi) {
            list.clear()
            list.addAll(sepet)
            yemeklerSunucudanAlindi = true

        }
    }
    fun sepettekiYemekler(): List<SepetYemekler> = list

    fun yemekEkle(yemek: Yemekler, adet: Int, repository: YemeklerRepository) {
        var eklendi = false
        for (y in list) {
            if (y.sepet_yemek_id == yemek.yemek_id) {
                        eklendi = true
                break
            }
        }

        if (eklendi) {
            for (y in list) {
                if (y.sepet_yemek_id == yemek.yemek_id) {
                    y.yemek_siparis_adet += adet
                    CoroutineScope(Dispatchers.Main).launch {

                        val yemekSilindi = repository.yemekSil(y.sepet_yemek_id, C.kullanici_adi)

                        if (yemekSilindi.success != "0") {
                            repository.sepeteYemekleriEkle(
                                y.sepet_yemek_id,
                                y.yemek_adi,
                                y.yemek_resim_adi,
                                y.yemek_fiyat,
                                y.yemek_siparis_adet,
                                C.kullanici_adi
                            )
                        }
                    }
                    break
                }
            }
        } else {
            val y = SepetYemekler.from(yemek)
            y.yemek_siparis_adet = adet
            CoroutineScope(Dispatchers.Main).launch {
                repository.sepeteYemekleriEkle(
                    y.sepet_yemek_id,
                    y.yemek_adi,
                    y.yemek_resim_adi,
                    y.yemek_fiyat,
                    y.yemek_siparis_adet,
                    C.kullanici_adi
                )
                list.add(y)

            }
        }
    }

    fun yemekSil(yemek: SepetYemekler) {
        for (y in list) {
            if (y.sepet_yemek_id == yemek.sepet_yemek_id) {
                if (y.yemek_siparis_adet == 1) {
                    list.remove(y)
                } else {
                    y.yemek_siparis_adet--

                }
                break
            }
        }

    }

    }
}