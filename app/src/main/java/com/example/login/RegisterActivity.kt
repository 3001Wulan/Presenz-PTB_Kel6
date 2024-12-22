package com.example.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.LoginTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                RegisterScreen()
            }
        }
    }

    @Composable
    fun RegisterScreen() {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDCE6F4)) // Background color
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            val logo: Painter = painterResource(id = R.drawable.logo) // Replace with your logo resource
            Image(painter = logo, contentDescription = "Logo", modifier = Modifier.size(100.dp))

            Spacer(modifier = Modifier.height(8.dp))

            // Register Title
            Text("REGISTER", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            Spacer(modifier = Modifier.height(16.dp))

            // First Name Field
            InputFieldWithIcon(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = "Input your first name here",
                icon = painterResource(id = R.drawable.ic_user) // Replace with your icon resource
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Last Name Field
            InputFieldWithIcon(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = "Input your last name here",
                icon = painterResource(id = R.drawable.ic_user1) // Replace with your icon resource
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Email Field
            InputFieldWithIcon(
                value = email,
                onValueChange = { email = it },
                placeholder = "Input your email address here",
                icon = painterResource(id = R.drawable.ic_email) // Replace with your icon resource
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Password Field
            InputFieldWithIcon(
                value = password,
                onValueChange = { password = it },
                placeholder = "Input your password here",
                icon = painterResource(id = R.drawable.ic_lock), // Replace with your icon resource
                isPassword = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Register Button
            Button(
                onClick = {
                    if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                        Toast.makeText(this@RegisterActivity, "Registration successful!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7DA7DB))
            ) {
                Text("Register", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

    @Composable
    fun InputFieldWithIcon(
        value: String,
        onValueChange: (String) -> Unit,
        placeholder: String,
        icon: Painter,
        isPassword: Boolean = false
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), shape = MaterialTheme.shapes.small)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp).padding(end = 8.dp)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(placeholder, color = Color.Gray, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )
        }
    }
}
