package com.example.tbptb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack // Import ArrowBack icon
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R

class UbahPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UbahPasswordScreen { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun UbahPasswordScreen(onSaveClick: (String) -> Unit) {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.background), // Ganti dengan nama resource gambar Anda
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Back arrow icon
        IconButton(
            onClick = {
                // Handle the back button logic (e.g., navigate back)
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart) // Align icon to the top-left
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black // Color for the back arrow icon
            )
        }

        // Main content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Title with bold and black color
            Text(
                text = "Ubah Password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            // Separate blue boxes for each password input
            PasswordInputFieldContainer(
                label = "Password Sekarang",
                value = currentPassword,
                onValueChange = { currentPassword = it },
                isPasswordVisible = isPasswordVisible,
                onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInputFieldContainer(
                label = "Password Baru",
                value = newPassword,
                onValueChange = { newPassword = it },
                isPasswordVisible = isPasswordVisible,
                onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInputFieldContainer(
                label = "Konfirmasi Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                isPasswordVisible = isPasswordVisible,
                onVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    when {
                        currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty() -> {
                            onSaveClick("Harap isi semua kolom")
                        }
                        newPassword != confirmPassword -> {
                            onSaveClick("Password baru dan konfirmasi tidak cocok")
                        }
                        else -> {
                            onSaveClick("Password berhasil diubah")
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF73A0D7),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Simpan", fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun PasswordInputFieldContainer(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Box akan menyesuaikan lebar elemen induknya
            .background(Color.Gray, shape = RoundedCornerShape(6.dp)) // Warna latar belakang border
            .border(
                width = 3.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(6.dp) // Bentuk sudut border dan latar belakang harus sama
            )
            .padding(0.dp) // Hilangkan padding agar tabel biru tidak keluar
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth() // Lebar biru sesuai dengan border
                .background(Color(0xFFE8EFF8), shape = RoundedCornerShape(6.dp)) // Warna biru dengan bentuk sesuai border
                .padding(8.dp) // Tambahkan padding dalam untuk konten
        ) {
            PasswordInputField(
                label = label,
                value = value,
                onValueChange = onValueChange,
                isPasswordVisible = isPasswordVisible,
                onVisibilityToggle = onVisibilityToggle
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onVisibilityToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock icon"
            )
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onVisibilityToggle) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun UbahPasswordPreview() {
    UbahPasswordScreen { /* Preview Only */ }
}
