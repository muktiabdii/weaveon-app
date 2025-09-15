package com.example.hology.ui.common

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.zIndex
import com.example.hology.R
import com.example.hology.domain.model.ExerciseHistoryUi
import com.example.hology.ui.theme.Primary09
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExerciseCarousel(
    exerciseItems: List<ExerciseHistoryUi>,
    onSeeAllClick: () -> Unit = {}
) {
    val displayItems = exerciseItems.take(3)
    val hasSeeAll = exerciseItems.size > 3
    val totalPages = displayItems.size + if (hasSeeAll) 1 else 0
    val pagerState = rememberPagerState(pageCount = { totalPages })

    Column(modifier = Modifier.fillMaxWidth()) {

        // section title
        Text(
            text = "Jejak Exercise Si Kecil",
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 18.sp,
            color = Primary09
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (exerciseItems.isEmpty()) {
            // fallback kalau kosong
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Belum ada jejak exercise",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        } else {
            // kalau ada isi
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    pageSpacing = (-30).dp,
                    contentPadding = PaddingValues(horizontal = 40.dp)
                ) { page ->
                    if (page < displayItems.size) {
                        // tampilkan card
                        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                        val scale by animateFloatAsState(targetValue = if (pageOffset < 0.5) 1f else 0.9f)
                        val alpha by animateFloatAsState(targetValue = if (pageOffset < 0.5) 1f else 0.5f)

                        Box(
                            modifier = Modifier
                                .zIndex(if (pageOffset < 0.5) 1f else 0f)
                                .graphicsLayer {
                                    this.scaleX = scale
                                    this.scaleY = scale
                                    this.alpha = alpha
                                }
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                        ) {
                            ExerciseCarouselCard(
                                exerciseItem = displayItems[page],
                                isActive = page == pagerState.currentPage
                            )
                        }
                    } else if (hasSeeAll) {
                        // item terakhir: tombol lihat semua
                        ExerciseCarouselHasSeeAllCard(
                            onClick = onSeeAllClick
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // pagination dots
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(totalPages) { index ->
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
}
