package com.example.yemekuygulamasi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemekuygulamasi.data.endity.Yemekler
import com.example.yemekuygulamasi.databinding.CardTasarimBinding
import com.example.yemekuygulamasi.ui.fragment.AnasayfaFragment
import com.example.yemekuygulamasi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel

class YemeklerAdapter(
    var mContext: Context,
    var yemeklerListesi: List<Yemekler>,
    var viewModel: ViewModel
) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(var tasarim: CardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemekler = yemeklerListesi[position]
        val t = holder.tasarim
        t.baslikYemek.text = yemekler.yemek_adi
        t.aciklamaYemek.text = yemekler.yemek_fiyat.toString()+"₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemekler.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).into(t.yemeklerMage)

        t.yemeklerCard.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(yemekDetay = yemekler)
            Navigation.findNavController(it).navigate(gecis)
        }

    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }


}