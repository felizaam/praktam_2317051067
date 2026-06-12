package com.example.praktam_2317051067.data.model

import com.google.gson.annotations.SerializedName

data class Buku(
    val judul: String,
    val penulis: String,
    val kategori: String,
    val tahun: Int,
    @SerializedName("image_url")
    val imageUrl: String
)