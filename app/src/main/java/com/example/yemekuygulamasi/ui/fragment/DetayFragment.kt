package com.example.yemekuygulamasi.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yemekuygulamasi.R
import com.example.yemekuygulamasi.data.endity.Yemekler
import com.example.yemekuygulamasi.databinding.FragmentDetayBinding
import com.example.yemekuygulamasi.ui.viewmodel.AnasayfaViewModel
import com.example.yemekuygulamasi.ui.viewmodel.DetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private val viewModel: DetayViewModel by viewModels()
    private val bundle: DetayFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setData(bundle.yemekDetay)
        initUi()
        viewModel.yemek.observe(viewLifecycleOwner) {
            binding.adet.text = it.yemek_siparis_adet.toString()
        }
    }

    private fun initUi() {
        val gelenYemek = bundle.yemekDetay
        binding.textViewDetay.text = gelenYemek.yemek_adi
        binding.textViewFiyat.text = gelenYemek.yemek_fiyat.toString()+"₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(binding.imageViewYemek.context).load(url).into(binding.imageViewYemek)

        binding.buttonSepeteEkle.setOnClickListener {
            if (binding.adet.text != "0"){
                viewModel.sepeteEkle()
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Ürünler sepete başarıyla eklendı",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                Toast.makeText(requireContext(), "En az 1 adet ürün eklemelisiniz!", Toast.LENGTH_SHORT).show()
            }


        }
        binding.buttonArti.setOnClickListener {
            viewModel.adetArttir()
            val adet = (binding.adet.text).toString().toInt()
            val arttir = gelenYemek.yemek_fiyat*adet
            binding.textViewFiyat.text = arttir.toString()+"₺"
        }
        binding.buttonEksi.setOnClickListener {
            if(binding.adet.text.toString()!="0"){
                viewModel.adetAzalt()
                val adet = (binding.adet.text).toString().toInt()
                val azalt = gelenYemek.yemek_fiyat*adet
                binding.textViewFiyat.text = azalt.toString()+"₺"
            }


        }

    }
}