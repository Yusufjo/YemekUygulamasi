package com.example.yemekuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemekuygulamasi.databinding.FragmentSepetBinding
import com.example.yemekuygulamasi.ui.adapter.SepetAdapter
import com.example.yemekuygulamasi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private val viewModel: SepetViewModel by viewModels()

    //private val bundle: SepetFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSepet.layoutManager = LinearLayoutManager(requireContext())
        viewModelGözle()
        viewModel.yemekleriYukle()
    }

    private fun viewModelGözle() {
        sepettekiYemekleriGözle()
        yemekSilindiGözle()
    }

    private fun yemekSilindiGözle() {
        viewModel.yemekSilindi.observe(viewLifecycleOwner) { cevap ->
            if (cevap.success != "0") {
                viewModel.yemegiEkrandanSil()
            } else {
                Toast.makeText(requireContext(), "Hata! ${cevap.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun sepettekiYemekleriGözle() {
        viewModel.sepettekiYemeklerListesi.observe(viewLifecycleOwner) {
            it?.let {
                val sepetAdapter = SepetAdapter(requireContext(), it, viewModel)
                val lst = sepetAdapter.sepetListesi
                var adet = 0
                for (y in lst) {
                    adet += y.yemek_fiyat*y.yemek_siparis_adet
                }


                binding.rvSepet.adapter = sepetAdapter
                binding.textViewToplam.text = "Toplam Fiyat : $adet₺"


            }
        }
    }

}



