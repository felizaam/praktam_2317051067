package com.example.praktam_2317051067

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
    val kategoriList = listOf("Semua", "Novel Sejarah", "Fantasi", "Pengembangan Diri")

    var kategoriDipilih by remember { mutableStateOf("Semua") }

    val bukuTampil = if (kategoriDipilih == "Semua") {
        daftarBuku
    } else {
        daftarBuku.filter { it.kategori == kategoriDipilih }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F2E8))
            .padding(18.dp)
    ) {
        Text(
            text = "BookNest",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Text(
            text = "Temukan buku pilihan sesuai kategori favoritmu.",
            fontSize = 14.sp,
            color = Color(0xFF6D4C41)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Kategori",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(kategoriList) { kategori ->
                Button(
                    onClick = {
                        kategoriDipilih = kategori
                    }
                ) {
                    Text(text = kategori)
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Daftar Buku",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(bukuTampil) { buku ->
                ItemBuku(buku = buku)
            }
        }
    }
}

@Composable
fun ItemBuku(buku: Buku) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = buku.judul,
                modifier = Modifier
                    .size(95.dp)
                    .background(Color(0xFFD7CCC8)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = buku.judul,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2723)
                )

                Spacer(modifier = Modifier.height(4.dp))

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
}

@Preview(showBackground = true)
@Composable
fun PreviewBookNestScreen() {
    MaterialTheme {
        BookNestScreen()
    }
}