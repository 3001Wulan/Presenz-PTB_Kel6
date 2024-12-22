package com.example.presenz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Profil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfilScreen(
                name = "Karina, M.Kom",
                bio = "NIP: 123334",
                email = "karina@gmail.com",
                phoneNumber = "+62XXXXXXXXX22",
                gender = "Perempuan",
                birthDate = "Atur",
                password = "12345",
                onBackClick = { finish() }, // Kembali ke layar sebelumnya
                onSaveClick = { /* Tambahkan logika penyimpanan di sini */ }
            )
        }
    }
}

@Composable
fun ProfilScreen(
    name: String,
    bio: String,
    email: String,
    phoneNumber: String,
    gender: String,
    birthDate: String,
    password: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    val imagePainter = painterResource(id = R.drawable.profil)
    val backgroundPainter = painterResource(id = R.drawable.backgorund)

    Box(modifier = Modifier.fillMaxSize()) {
        // Gambar latar belakang
        Image(
            painter = backgroundPainter,
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Tombol "Back" di sudut kiri atas
        IconButton(
            onClick = { onBackClick() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Tombol "Save" (ikon centang) di sudut kanan atas
        IconButton(
            onClick = { onSaveClick() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Save",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Profil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Foto Profil
            Image(
                painter = imagePainter,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )

            // Tombol Edit di bawah foto profil
            Button(
                onClick = { /* Handle Edit button click */ },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Edit Profil",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Elemen dalam Card terpisah
            EditableProfileCard(label = "Name", value = name)
            EditableProfileCard(label = "Bio", value = bio)
            EditableProfileCard(label = "Email", value = email)
            EditableProfileCard(label = "No. Handphone", value = phoneNumber)
            EditableProfileCard(label = "Jenis Kelamin", value = gender)
            EditableProfileCard(label = "Tanggal Lahir", value = birthDate)
            EditableProfileCard(label = "Ubah Password", value = password)

            Spacer(modifier = Modifier.weight(1f))

            // Navigation Bar
            ProfilBottomNavigationBar()
        }
    }
}

@Composable
fun EditableProfileCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            IconButton(
                onClick = { /* Handle Edit */ },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit $label",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ProfilBottomNavigationBar() {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(0.dp),
        backgroundColor = Color.White,
        elevation = 16.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(45.dp)) },
            selected = false,
            onClick = { /* Tindakan untuk tombol Home */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu", modifier = Modifier.size(45.dp)) },
            selected = false,
            onClick = { /* Tindakan untuk tombol Menu */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings", modifier = Modifier.size(45.dp)) },
            selected = false,
            onClick = { /* Tindakan untuk tombol Settings */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profil", modifier = Modifier.size(45.dp)) },
            selected = false,
            onClick = { /* Tindakan untuk tombol Profil */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfilScreen() {
    ProfilScreen(
        name = "Karina, M.Kom",
        bio = "NIP: 123334",
        email = "karina@gmail.com",
        phoneNumber = "+62XXXXXXXXX22",
        gender = "Perempuan",
        birthDate = "Atur",
        password = "12345",
        onBackClick = { /* Preview Back Action */ },
        onSaveClick = { /* Preview Save Action */ }
    )
}
