package com.example.yemekuygulamasi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.endity.SepetYemekler
import com.example.yemekuygulamasi.data.endity.Yemekler
import com.example.yemekuygulamasi.data.endity.YemeklerCRUDCevap
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.example.yemekuygulamasi.store.Sepet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository) : ViewModel() {
//    var yemeklerCRUDCevap =
val yemek = MutableLiveData<SepetYemekler>()
    val sepeteEkleResponse = MutableLiveData<String>()

    fun adetAzalt() {
        if (yemek.value?.yemek_siparis_adet != 0) {
            yemek.value?.let {
                it.yemek_siparis_adet--
                yemek.value = yemek.value

            }
        }
    }

    fun adetArttir() {
        yemek.value?.let {
            it.yemek_siparis_adet++
            yemek.value = yemek.value
        }
    }

    fun setData(yemek: Yemekler) {
        this.yemek.value = SepetYemekler.from(yemek)
    }

    fun sepeteEkle() {
        yemek.value?.let { y ->
            val yemek    = Yemekler( y.sepet_yemek_id,
                y.yemek_adi, y.yemek_resim_adi,
                y.yemek_fiyat)
            Sepet.Sepet.yemekEkle(yemek, y.yemek_siparis_adet, yemeklerRepository) }
    }
}