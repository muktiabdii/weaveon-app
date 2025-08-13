package com.example.weaveon.presentation.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import com.example.weaveon.R
import com.example.weaveon.domain.model.ExerciseItem
import com.example.weaveon.presentation.ui.theme.Primary09
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExerciseCarousel(exerciseItems: List<ExerciseItem>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Section title
        Text(
            text = "Jejak Exercise Si Kecil",
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 18.sp,
            color = Primary09
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Carousel
        val pagerState = rememberPagerState(pageCount = { exerciseItems.size })

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Tinggi Box carousel untuk card 160.dp
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth(),
                pageSpacing = (-30).dp, // Negative spacing untuk efek stacked
                contentPadding = PaddingValues(horizontal = 40.dp)
            ) { page ->
                val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                // Calculate scale animation
                val scale by animateFloatAsState(targetValue = if (pageOffset < 0.5) 1f else 0.9f)
                val alpha by animateFloatAsState(targetValue = if (pageOffset < 0.5) 1f else 0.5f) // Alpha untuk efek buram

                // Card container dengan scale dan zIndex
                Box(
                    modifier = Modifier
                        .zIndex(if (pageOffset < 0.5) 1f else 0f) // Card aktif di atas
                        .graphicsLayer {
                            this.scaleX = scale
                            this.scaleY = scale
                            this.alpha = alpha
                        }
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    ExerciseCard(
                        exerciseItem = exerciseItems[page],
                        isActive = page == pagerState.currentPage
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Pagination dots
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(exerciseItems.size) { index ->
                val isSelected = index == pagerState.currentPage
                val size by animateDpAsState(targetValue = if (isSelected) 10.dp else 8.dp)
                val color = if (isSelected) Color(0xFF703D2A) else Color.Gray.copy(alpha = 0.3f)

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(size)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}
