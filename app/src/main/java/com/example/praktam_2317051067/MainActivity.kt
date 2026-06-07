package com.example.praktam_2317051067

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                DaftarBukuScreen()
            }
        }
    }
}

@Composable
fun DaftarBukuScreen() {
    val daftarBuku = BukuSource.daftarBuku

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F2E8))
            .padding(20.dp)
    ) {
        Text(
            text = "BookNest",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Text(
            text = "Daftar Buku Pilihan",
            fontSize = 16.sp,
            color = Color(0xFF6D4C41)
        )

        Spacer(modifier = Modifier.height(18.dp))

        daftarBuku.forEach { buku ->
            ItemBuku(buku = buku)
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun ItemBuku(buku: Buku) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(14.dp)
    ) {
        Text(
            text = buku.judul,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3E2723)
        )

        Text(
            text = "Penulis: ${buku.penulis}",
            fontSize = 14.sp,
            color = Color(0xFF5D4037)
        )

        Text(
            text = "Kategori: ${buku.kategori}",
            fontSize = 14.sp,
            color = Color(0xFF5D4037)
        )

        Text(
            text = "Tahun Terbit: ${buku.tahun}",
            fontSize = 14.sp,
            color = Color(0xFF5D4037)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDaftarBukuScreen() {
    MaterialTheme {
        DaftarBukuScreen()
    }
}