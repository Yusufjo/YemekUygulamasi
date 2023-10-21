package com.example.yemekuygulamasi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekuygulamasi.data.endity.Yemekler
import com.example.yemekuygulamasi.data.repo.YemeklerRepository
import com.example.yemekuygulamasi.store.Sepet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yemeklerRepository: YemeklerRepository) : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    fun yemekleriYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value = yemeklerRepository.yemekleriYukle()
        }
    }

    fun sepetiYükle() {
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerRepository.sepettekiYemekleriYukle()?.let { Sepet.Sepet.sepetiYukle(it) }
        }
    }
}