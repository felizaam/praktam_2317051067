package com.example.praktam_2317051067.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2317051067.data.model.Buku
import com.example.praktam_2317051067.data.model.UserAccount
import com.example.praktam_2317051067.data.repository.BukuRepository
import com.example.praktam_2317051067.ui.screens.DetailBukuScreen
import com.example.praktam_2317051067.ui.screens.HomeScreen
import com.example.praktam_2317051067.ui.screens.LoginScreen
import com.example.praktam_2317051067.ui.screens.ProfileScreen
import com.example.praktam_2317051067.ui.screens.RegisterScreen
import kotlinx.coroutines.launch

private const val BUKU_API_URL =
    "https://gist.githubusercontent.com/felizaam/a29c809977d99c10c6db532dc279be27/raw/c48efa2304d40cafced2b7b8783498112414694e/daftar_buku.json"

@Composable
fun BookNestApp() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    var akunTerdaftar by remember {
        mutableStateOf(
            UserAccount(
                nama = "Feliza",
                email = "feliza@mail.com",
                password = "123456"
            )
        )
    }

    var userLogin by remember { mutableStateOf<UserAccount?>(null) }
    var daftarBuku by remember { mutableStateOf<List<Buku>>(emptyList()) }
    var isLoadingData by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    suspend fun loadBuku() {
        isLoadingData = true

        try {
            daftarBuku = BukuRepository.getBuku(BUKU_API_URL)
            errorMessage = null
        } catch (e: Exception) {
            daftarBuku = BukuRepository.getFallbackBuku()
            errorMessage = "Data dari Gist gagal dimuat. Menampilkan data cadangan."
        }

        isLoadingData = false
    }

    LaunchedEffect(Unit) {
        loadBuku()
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                akunTerdaftar = akunTerdaftar,
                onLoginSuccess = { user ->
                    userLogin = user
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { akunBaru ->
                    akunTerdaftar = akunBaru
                    userLogin = akunBaru
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("home") {
            HomeScreen(
                navController = navController,
                user = userLogin,
                daftarBuku = daftarBuku,
                isLoadingData = isLoadingData,
                errorMessage = errorMessage,
                onRefresh = {
                    scope.launch {
                        loadBuku()
                    }
                }
            )
        }

        composable("detail/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0

            DetailBukuScreen(
                index = index,
                daftarBuku = daftarBuku,
                navController = navController
            )
        }

        composable("profile") {
            ProfileScreen(
                user = userLogin,
                totalBuku = daftarBuku.size,
                onBackHome = {
                    navController.popBackStack()
                },
                onLogout = {
                    userLogin = null
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}