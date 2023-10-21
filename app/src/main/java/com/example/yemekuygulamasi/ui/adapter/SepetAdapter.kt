package com.example.yemekuygulamasi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemekuygulamasi.data.endity.SepetYemekler
import com.example.yemekuygulamasi.databinding.CardTasarimSepetBinding
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewModel

class SepetAdapter(
    var mContext: Context,
    var sepetListesi: List<SepetYemekler>,
    var viewModel: SepetViewModel
) :
    RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>() {

    inner class SepetCardTasarimTutucu(var tasarim: CardTasarimSepetBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val binding = CardTasarimSepetBinding.inflate(LayoutInflater.from(mContext),
            parent,false)
        return SepetCardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepet = sepetListesi[position]
        val t = holder.tasarim
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).into(t.imageViewSepet)
        t.baslikYemek.text = sepet.yemek_adi
        val toplam = sepet.yemek_fiyat*sepet.yemek_siparis_adet
        t.fiyat.text = toplam.toString()+"₺"
        t.adet.text = sepet.yemek_siparis_adet.toString() + " adet"
        t.btnSil.setOnClickListener {
            viewModel.yemegiSunucudanSil(sepet.sepet_yemek_id,sepet.kullanici_adi)
        }

    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }
}