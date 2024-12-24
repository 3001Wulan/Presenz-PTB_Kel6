package com.example.presenz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.login.R

class PopupArsip : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopupArsipScreen()
        }
    }
}

@Composable
fun PopupArsipScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE8EFF8)), // Warna background utama
        contentAlignment = Alignment.Center
    ) {
        // Pop-up card
        Column(
            modifier = Modifier
                .width(350.dp)
                .background(Color(0xFFD9D9D9), RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Badge icon
            Box(
                modifier = Modifier
                    .size(250.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.popup), // Ganti dengan nama file Anda
                    contentDescription = "Success Icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Text
            Text(
                text = "Absen Berhasil Diarsipkan !!",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Close button
            Button(
                onClick = { /* Handle close action */ },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF73A0D7))
            ) {
                Text(
                    text = "Close",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopupArsipPreview() {
    AttendanceUpdatedScreen()
}


