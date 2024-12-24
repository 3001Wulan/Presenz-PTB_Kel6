package com.example.presenz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                HomePage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Hello, Karina\nHave a nice day!",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notification Icon",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 8.dp)
                    )
                }
            )
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Konten di atas latar belakang tanpa gambar

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TablePage()
            }
        }
    }
}

@Composable
fun TablePage() {
    val rows = listOf(
        TableRowData(Icons.Outlined.Group, "Rekap Pertemuan"),
        TableRowData(Icons.Outlined.Checklist, "Laporan Absensi"),
        TableRowData(Icons.Outlined.Folder, "Arsip Absensi")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(rows.size) { index ->
            TableCard(rowData = rows[index])
        }
    }
}

@Composable
fun TableCard(rowData: TableRowData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .background(Color(0xFFE8EFF8)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE8EFF8))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = rowData.icon,
                contentDescription = rowData.title,
                modifier = Modifier
                    .size(48.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = rowData.title,
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

data class TableRowData(
    val icon: ImageVector,
    val title: String
)

@Composable
fun BottomNavigationBar() {
    BottomAppBar {
        IconButton(
            onClick = { /* Navigasi ke Home */ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home Icon",
                modifier = Modifier.size(48.dp)
            )
        }
        IconButton(
            onClick = { /* Navigasi ke Menu Grid */ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.GridView,
                contentDescription = "Grid Menu Icon",
                modifier = Modifier.size(48.dp)
            )
        }
        IconButton(
            onClick = { /* Navigasi ke Settings */ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings Icon",
                modifier = Modifier.size(48.dp)
            )
        }
        IconButton(
            onClick = { /* Navigasi ke Profil */ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Icon",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    MaterialTheme {
        HomePage()
    }
}
