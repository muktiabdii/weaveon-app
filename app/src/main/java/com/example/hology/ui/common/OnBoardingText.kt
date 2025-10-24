package com.example.hology.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hology.ui.theme.Primary09
import com.example.hology.R
import com.example.hology.ui.common.PageIndicators
import com.example.hology.ui.theme.Primary08

@Composable
fun OnBoardingText(
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // title
        Text(
            text = when (currentPage) {
                0 -> "Selamat Datang di Aplikasi WeaveOn!"
                1 -> "Kenali Minat si Kecil dengan Wevy"
                2 -> "Laporan Perkembangan Intuitif"
                else -> "Exercise Edukatif, Anak Lebih Aktif"
            },
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            color = Primary09,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // deskription
        Text(
            text = when (currentPage) {
                0 -> "Temani perjalanan anak Anda menemukan potensi minat dan bakatnya melalui aktivitas belajar yang seru dan personal."
                1 -> "Cukup rekam aktivitas bermain si kecil, dan teknologi AI Wevy akan menganalisis ekspresi serta fokusnya. Temukan potensi dan kegiatan favoritnya secara objektif dan menyenangkan!"
                2 -> "Lihat rangkuman hasil analisis Wevy dalam bentuk grafik yang mudah dibaca. Bandingkan tingkat ketertarikan anak pada setiap kegiatan dan dapatkan rekomendasi aktivitas selanjutnya."
                else -> "Ikuti berbagai tantangan seru dan pantau perkembangan anak Anda secara real-time!"
            },
            fontSize = 16.sp,
            color = Primary08,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}
