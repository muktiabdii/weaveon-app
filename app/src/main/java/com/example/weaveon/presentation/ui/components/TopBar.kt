package com.example.weaveon.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Secondary09

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackClick: () -> Unit,
    Image: Painter? = null, // Gambar opsional
) {
    Surface(
        shadowElevation = 4.dp,
        color = Color(0xFFFEFEFE)
    ) {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        color = Secondary09,
                        textAlign = TextAlign.Center
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Secondary09,
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            actions = {
                if (Image != null) {
                    Image(
                        painter = Image,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Spacer(modifier = Modifier
                        .padding(end = 8.dp)
                        .size(40.dp))
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent // Karena sudah dibungkus Surface
            )
        )
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(
        title = "Aibu",
        onBackClick = {},
    )
}
