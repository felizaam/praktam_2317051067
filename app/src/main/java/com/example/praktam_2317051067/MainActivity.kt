package com.example.praktam_2317051067

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam_2317051067.model.Buku
import com.example.praktam_2317051067.model.BukuSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BookNestScreen()
            }
        }
    }
}

@Composable
fun BookNestScreen() {
    val daftarBuku = BukuSource.daftarBuku
    val bukuFavorit = listOf("Laut Bercerita", "Bumi")

    var kataKunci by remember { mutableStateOf("") }
    var tampilkanFavorit by remember { mutableStateOf(false) }
    var jumlahKlik by remember { mutableStateOf(0) }

    val bukuTampil = daftarBuku.filter { buku ->
        val cocokSearch = buku.judul.contains(kataKunci, ignoreCase = true) ||
                buku.penulis.contains(kataKunci, ignoreCase = true) ||
                buku.kategori.contains(kataKunci, ignoreCase = true)

        val cocokFavorit = if (tampilkanFavorit) {
            bukuFavorit.contains(buku.judul)
        } else {
            true
        }

        cocokSearch && cocokFavorit
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F2E8))
            .verticalScroll(rememberScrollState())
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp)
                .background(Color(0xFF6D4C41)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "BookNest",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Katalog Buku Digital",
                    fontSize = 16.sp,
                    color = Color(0xFFFFE0B2)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Cari Buku",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = kataKunci,
            onValueChange = { kataKunci = it },
            label = { Text("Masukkan judul, penulis, atau kategori") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tampilkan buku favorit",
                fontSize = 14.sp,
                color = Color(0xFF4E342E)
            )

            Switch(
                checked = tampilkanFavorit,
                onCheckedChange = { tampilkanFavorit = it }
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                jumlahKlik++
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Refresh Rekomendasi ($jumlahKlik)")
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Daftar Buku",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (bukuTampil.isEmpty()) {
            Text(
                text = "Buku tidak ditemukan.",
                fontSize = 14.sp,
                color = Color(0xFF5D4037)
            )
        } else {
            bukuTampil.forEach { buku ->
                ItemBuku(buku = buku)
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}

@Composable
fun ItemBuku(buku: Buku) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = buku.judul,
            modifier = Modifier
                .size(90.dp)
                .background(Color(0xFFD7CCC8)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = buku.judul,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Text(
                text = "Penulis: ${buku.penulis}",
                fontSize = 13.sp,
                color = Color(0xFF5D4037)
            )

            Text(
                text = "Kategori: ${buku.kategori}",
                fontSize = 13.sp,
                color = Color(0xFF5D4037)
            )

            Text(
                text = "Tahun: ${buku.tahun}",
                fontSize = 13.sp,
                color = Color(0xFF5D4037)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { }) {
                Text(
                    text = "Lihat Detail",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookNestScreen() {
    MaterialTheme {
        BookNestScreen()
    }
}