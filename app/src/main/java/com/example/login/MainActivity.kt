package com.example.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Menampilkan splash screen dengan logo
        setContent {
            LoginTheme {
                SplashScreen()  // Calling SplashScreen() correctly inside setContent
            }
        }

        // Menunda pengalihan ke LoginActivity selama 5 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Menutup MainActivity setelah pengalihan
        }, 2000) // 5000 ms = 5 detik
    }
}

@Composable
fun SplashScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF73A0D7)) // Set background color to #73A0D7
    ) {
        // Mengatur ukuran logo menjadi lebih kecil dengan Modifier.size
        Image(
            painter = painterResource(id = R.drawable.i_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp) // Mengatur ukuran gambar menjadi 150 dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    LoginTheme {
        SplashScreen()
    }
}
