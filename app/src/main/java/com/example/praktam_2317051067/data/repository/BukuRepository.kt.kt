package com.example.praktam_2317051067.data.repository

import com.example.praktam_2317051067.data.api.RetrofitClient
import com.example.praktam_2317051067.data.model.Buku

object BukuRepository {

    suspend fun getBuku(url: String): List<Buku> {
        return RetrofitClient.apiService.getBuku(url)
    }

    fun getFallbackBuku(): List<Buku> {
        return listOf(
            Buku(
                judul = "Laut Bercerita",
                penulis = "Leila S. Chudori",
                kategori = "Novel Sejarah",
                tahun = 2017,
                imageUrl = ""
            ),
            Buku(
                judul = "Bumi",
                penulis = "Tere Liye",
                kategori = "Fantasi",
                tahun = 2014,
                imageUrl = ""
            ),
            Buku(
                judul = "Filosofi Teras",
                penulis = "Henry Manampiring",
                kategori = "Pengembangan Diri",
                tahun = 2018,
                imageUrl = ""
            )
        )
    }
}