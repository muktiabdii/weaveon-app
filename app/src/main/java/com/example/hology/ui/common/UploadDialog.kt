package com.example.hology.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.hology.R
import com.example.hology.ui.theme.Secondary01
import com.example.hology.ui.theme.Secondary03
import com.example.hology.ui.theme.Secondary05
import com.example.hology.ui.theme.Secondary09

@Composable
fun UploadDialog(
    onDismiss: () -> Unit,
    onUploadClick: () -> Unit,
    onFeedbackSelected: (String) -> Unit,
    selectedFeedback: String? = null,
    uploadedImageUrl: String? = null,
    isUploading: Boolean = false,
    isSaving: Boolean = false,
    onSaveClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dokumentasi & Penilaian",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Secondary09
                        ),
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Tutup",
                        tint = Secondary09,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onDismiss() }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Upload Section
                if (isUploading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Secondary09)
                    }
                } else if (uploadedImageUrl != null) {
                    AsyncImage(
                        model = uploadedImageUrl,
                        contentDescription = "Uploaded Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clickable { onUploadClick() },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3EF)),
                        border = BorderStroke(1.dp, Secondary09)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_upload),
                                contentDescription = "Upload",
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Klik di sini untuk unggah foto",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Feedback Prompt
                Text(
                    text = "Bagaimana respon si kecil saat melakukan exercise tadi?",
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = Secondary09,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Emoji Feedback dengan distribusi rapi
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val feedbackOptions = listOf("Senang", "Bosan", "Sedih")
                    val emojiDrawables = listOf(
                        R.drawable.emot_happy,
                        R.drawable.emot_flat,
                        R.drawable.emot_sad
                    )

                    feedbackOptions.forEachIndexed { index, feedback ->
                        FeedbackOption(
                            emojiDrawable = emojiDrawables[index],
                            label = feedback,
                            isSelected = selectedFeedback == feedback,
                            isOtherSelected = selectedFeedback != null && selectedFeedback != feedback,
                            onClick = { onFeedbackSelected(feedback) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Submit Button
                Button(
                    onClick = { onSaveClick() },
                    enabled = !isSaving && uploadedImageUrl != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary05,
                        contentColor = Color.White,
                        disabledContainerColor = Secondary01,
                        disabledContentColor = Secondary03
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    if (isSaving) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Menyimpan...")
                    } else {
                        Text("Kirim Dokumentasi")
                    }
                }

            }
        }
    }
}

@Composable
fun FeedbackOption(
    emojiDrawable: Int,
    label: String,
    isSelected: Boolean,
    isOtherSelected: Boolean,
    onClick: () -> Unit
) {
    val alpha = when {
        isSelected -> 1f
        isOtherSelected -> 0.3f
        else -> 1f
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .alpha(alpha)
    ) {
        Image(
            painter = painterResource(id = emojiDrawable),
            contentDescription = label,
            modifier = Modifier.size(44.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = if (isSelected) FontFamily(Font(R.font.poppins_semibold)) else FontFamily(Font(R.font.poppins_medium)),
            color = if (isSelected) Secondary09 else Color(0xFF666666),
            textAlign = TextAlign.Center
        )
    }
}
