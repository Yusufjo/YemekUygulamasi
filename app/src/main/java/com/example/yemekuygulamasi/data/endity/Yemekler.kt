package com.example.yemekuygulamasi.data.endity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity
data class Yemekler(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "yemek_id")  var yemek_id: Int,
    @ColumnInfo(name = "yemek_adi") @NotNull var yemek_adi: String,
    @ColumnInfo(name = "yemek_resim_adi") @NotNull var yemek_resim_adi: String,
    @ColumnInfo(name = "yemek_fiyat") @NotNull var yemek_fiyat: Int
) : Serializable {
}