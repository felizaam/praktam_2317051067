package com.example.praktam_2317051067.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2317051067.R
import com.example.praktam_2317051067.data.model.Buku
import com.example.praktam_2317051067.data.model.UserAccount
import com.example.praktam_2317051067.ui.components.BukuCard

@Composable
fun HomeScreen(
    navController: NavController,
    user: UserAccount?,
    daftarBuku: List<Buku>,
    isLoadingData: Boolean,
    errorMessage: String?,
    onRefresh: () -> Unit
) {
    var kataKunci by remember { mutableStateOf("") }
    var hanyaBukuBaru by remember { mutableStateOf(false) }
    var kategoriDipilih by remember { mutableStateOf("Semua") }

    val kategoriList = listOf(
        "Semua",
        "Novel Sejarah",
        "Fantasi",
        "Pengembangan Diri"
    )

    val bukuTampil = daftarBuku.filter { buku ->
        val cocokSearch = buku.judul.contains(kataKunci, ignoreCase = true) ||
                buku.penulis.contains(kataKunci, ignoreCase = true) ||
                buku.kategori.contains(kataKunci, ignoreCase = true)

        val cocokTahun = if (hanyaBukuBaru) buku.tahun >= 2017 else true

        val cocokKategori = if (kategoriDipilih == "Semua") {
            true
        } else {
            buku.kategori.equals(kategoriDipilih, ignoreCase = true)
        }

        cocokSearch && cocokTahun && cocokKategori
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Halo, ${user?.nama ?: "Pengguna"}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Selamat datang di BookNest",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Button(
                onClick = {
                    navController.navigate("profile")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profil",
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(text = "Profil")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = kataKunci,
            onValueChange = { kataKunci = it },
            label = { Text("Cari judul, penulis, atau kategori") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Cari"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Filter",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Buku tahun 2017 ke atas",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Switch(
                checked = hanyaBukuBaru,
                onCheckedChange = { hanyaBukuBaru = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(kategoriList) { kategori ->
                Button(
                    onClick = {
                        kategoriDipilih = kategori
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (kategoriDipilih == kategori) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.secondary
                        }
                    )
                ) {
                    Text(text = kategori)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onRefresh,
            enabled = !isLoadingData,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = "Refresh",
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "Muat Ulang Buku")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoadingData) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Memuat data buku...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        } else {
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            Text(
                text = "Daftar Buku",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (bukuTampil.isEmpty()) {
                Text(
                    text = "Buku tidak ditemukan.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(bukuTampil) { _, buku ->
                        BukuCard(
                            buku = buku,
                            onDetailClick = {
                                val indexAsli = daftarBuku.indexOf(buku).coerceAtLeast(0)
                                navController.navigate("detail/$indexAsli")
                            }
                        )
                    }
                }
            }
        }
    }
}