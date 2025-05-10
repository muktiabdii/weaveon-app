package com.example.weaveon.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Custom shape for the curved bottom of the header
class BottomCurvedShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                // Start from top-left
                moveTo(0f, 0f)

                // Line to top-right
                lineTo(size.width, 0f)

                // Line to bottom-right
                lineTo(size.width, size.height - 40f)

                // Curve inward for profile picture indent
                cubicTo(
                    x1 = size.width * 0.75f,
                    y1 = size.height,
                    x2 = size.width * 0.25f,
                    y2 = size.height,
                    x3 = 0f,
                    y3 = size.height - 40f
                )

                // Close the path
                close()
            }
        )
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEF1F4))
    ) {
        // Top profile section with salmon color background
        ProfileHeader()

        // Menu options
        MenuOptions()

        Spacer(modifier = Modifier.weight(1f))

        // Bottom buttons
        BottomButtons()
    }
}

@Composable
fun ProfileHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Background with curved bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(
                    color = Color(0xFFFFC5B3),
                    shape = BottomCurvedShape()
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            // Top section with username and email
            Row(
                modifier = Modifier.padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(96.dp)) // Space for profile picture

                // Username and email
                Column {
                    Text(
                        text = "Username",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "email@gmail.com",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }

            // Profile picture that "pushes" into the background
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .offset(y = 30.dp) // Push downward to create overlap effect
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    }

    // Extra space to compensate for the profile picture overlap
    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun MenuOptions() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        MenuOption(title = "Edit Profil")
        MenuOption(title = "Memperbarui Data Anak")
        MenuOption(title = "Ganti Kata Sandi")
        MenuOption(title = "Tentang Aplikasi")
        MenuOption(title = "Syarat dan Ketentuan")
        MenuOption(title = "xxxxxxxxxxxxxxxx")
    }
}

@Composable
fun MenuOption(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color(0xFF8D4D40)
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Navigate",
                tint = Color(0xFF8D4D40)
            )
        }
    }
}

@Composable
fun BottomButtons() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(
            onClick = { /* Log out logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA78C))
        ) {
            Text(
                text = "Log out",
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black
            )
        }

        OutlinedButton(
            onClick = { /* Delete account logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text(
                text = "Hapus akun",
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Red
            )
        }
    }
}

// Preview annotation with specific dimensions to better visualize the screen
@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 740,
    name = "Profile Screen Preview"
)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}