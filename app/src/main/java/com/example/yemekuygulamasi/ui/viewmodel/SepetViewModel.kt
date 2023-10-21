package com.example.yemekuygulamasi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.endity.SepetYemekler
import com.example.yemekuygulamasi.data.endity.YemeklerCRUDCevap
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.example.yemekuygulamasi.store.Sepet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository) : ViewModel() {
    var sepettekiYemeklerListesi = MutableLiveData<List<SepetYemekler>?>()
    var yemekSilindi = MutableLiveData<YemeklerCRUDCevap>()
    private var yemekId: Int? = null

    fun yemekleriYukle() {
        sepettekiYemeklerListesi.value = Sepet.Sepet.sepettekiYemekler()
    }

    fun yemegiSunucudanSil(sepet_yemek_id: Int, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            sepettekiYemeklerListesi.value?.let { y ->
                yemekId = sepet_yemek_id
                yemekSilindi.value =
                    yemeklerRepository.yemekSil(sepet_yemek_id, kullanici_adi)
            }
        }
    }

    fun yemegiEkrandanSil() {
        sepettekiYemeklerListesi.value?.let { yemekler ->
            for (y in yemekler) {
                if (y.sepet_yemek_id == yemekId) {
                    Sepet.Sepet.yemekSil(y)
                    sepettekiYemeklerListesi.value = Sepet.Sepet.sepettekiYemekler()
                    break
                }
            }
        }

    }
}

