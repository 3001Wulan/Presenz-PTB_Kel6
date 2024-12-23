package com.example.presenz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import com.example.login.R
import com.example.login.SettingActivity
import com.example.login.CatatanPertemuanActivity


class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuScreen()
        }
    }
}

@Composable
fun MenuScreen() {
    val backgroundImage: Painter = painterResource(id = R.drawable.background)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = backgroundImage,
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
        )
        Column(modifier = Modifier.fillMaxSize()) {
            FeatureGrid()
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationBar()
        }
    }
}

@Composable
fun FeatureGrid() {
    val context = LocalContext.current
    val features = listOf(
        Pair("Kolaborasi Dosen", Icons.Filled.People),
        Pair("Absen Hybrid", Icons.Filled.Cloud),
        Pair("Pengelolaan Pertemuan", Icons.Filled.CalendarToday),
        Pair("Perubahan Absensi", Icons.Filled.Edit),
        Pair("Catatan Pertemuan", Icons.Filled.Note),
        Pair("Catatan Kehadiran", Icons.Filled.Assignment),
        Pair("Absen Praktikum", Icons.Filled.School),
        Pair("Absen Ujian", Icons.Filled.EditNote)
    )

    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        features.chunked(2).forEach { rowFeatures ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowFeatures.forEach { feature ->
                    FeatureCard(
                        title = feature.first,
                        icon = feature.second,
                        modifier = Modifier.weight(1f).padding(8.dp),
                        onClick = {
                            when (feature.first) {
                                "Catatan Pertemuan" -> {
                                    val intent =
                                        Intent(context, CatatanPertemuanActivity::class.java)
                                    context.startActivity(intent)
                                }
                                "Perubahan Absensi" -> {
                                    val intent = Intent(context, PerubahanAbsensiActivity::class.java)
                                    context.startActivity(intent)
                                }

                            }

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(60.dp),
                tint = Color.Black
            )

            Text(
                text = title,
                style = androidx.compose.ui.text.TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val context = LocalContext.current
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
            onClick = { /* Tindakan untuk tombol Search */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings", modifier = Modifier.size(45.dp)) },
            selected = false,
            onClick = {
                val intent = Intent(context, SettingActivity::class.java)
                context.startActivity(intent)
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profil", modifier = Modifier.size(45.dp)) },
            selected = false,
            onClick = {
                val intent = Intent(context, ProfilActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {
    MenuScreen()
}
