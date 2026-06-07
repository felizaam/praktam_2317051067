package com.example.praktam_2317051067

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BookNestIntroScreen()
            }
        }
    }
}

@Composable
fun BookNestIntroScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F2E8))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "BookNest",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E)
        )

        Text(
            text = "Katalog Buku Digital",
            fontSize = 18.sp,
            color = Color(0xFF6D4C41),
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Aplikasi sederhana untuk menampilkan daftar buku, informasi penulis, kategori, dan detail buku pilihan.",
            fontSize = 14.sp,
            color = Color(0xFF5D4037),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookNestIntroScreen() {
    MaterialTheme {
        BookNestIntroScreen()
    }
}