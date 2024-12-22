package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.ui.theme.LoginTheme

class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar() }
                ) { innerPadding ->
                    settingsScreen(modifier = Modifier.padding(innerPadding)) // Menambahkan padding dari Scaffold
                }
            }
        }
    }
}

@Composable
fun settingsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize() // Modifier diterapkan untuk menerima padding dari Scaffold
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_books),
            contentDescription = "Background Image", // Deskripsi aksesibilitas
            modifier = Modifier.fillMaxSize()
        )

        // Konten Utama
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            // Header dengan ikon kembali dan judul di tengah
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back", // Deskripsi ikon kembali
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* Handle Back */ },
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.weight(1f)) // Memberikan ruang untuk menempatkan teks di tengah

                Text(
                    text = "Setting",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.weight(1f)) // Memberikan ruang kosong di sebelah kanan
            }

            Spacer(modifier = Modifier.height(32.dp)) // Jarak antara header dan konten utama

            // Daftar pengaturan
            settingItem(Icons.Default.Person, "My Account")
            settingItem(Icons.Default.Lock, "Privacy and Security")
            settingItem(Icons.Default.Settings, "General")
            settingItem(Icons.Default.Language, "Language")
            settingItem(Icons.Default.Help, "Help Center")
            settingItem(Icons.Default.HeadsetMic, "Customer Service")
        }
    }
}

@Composable
fun settingItem(icon: ImageVector, title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Handle Click */ },
        shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp)), // Tetap konsisten dengan Material 3
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title, // Deskripsi aksesibilitas
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp) // Atur tinggi untuk memberi ruang lebih
    ) {
        // Ikon Home
        IconButton(onClick = { /* Handle Home */ }, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = MaterialTheme.colorScheme.onSurface, // Set warna ikon
                modifier = Modifier.size(32.dp) // Ukuran ikon lebih besar
            )
        }

        // Ikon Settings
        IconButton(onClick = { /* Handle Settings */ }, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onSurface, // Set warna ikon
                modifier = Modifier.size(32.dp) // Ukuran ikon lebih besar
            )
        }

        // Ikon Profile
        IconButton(onClick = { /* Handle Profile */ }, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = MaterialTheme.colorScheme.onSurface, // Set warna ikon
                modifier = Modifier.size(32.dp) // Ukuran ikon lebih besar
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewSettingsScreen() {
    LoginTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar() }
        ) { innerPadding ->
            settingsScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}
