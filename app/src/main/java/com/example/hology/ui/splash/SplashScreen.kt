package com.example.hology.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hology.R
import com.example.hology.ui.theme.Base
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SplashViewModel
) {
    var startAnimation by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(durationMillis = 1000, easing = { OvershootInterpolator(2f).getInterpolation(it) })
    )
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1000)
    )

    val isOnBoardingShown by viewModel.isOnBoardingShown().collectAsState(initial = false)
    val userUid by viewModel.getUserUidFlow().collectAsState(initial = null)

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2000)

        // kalau onboarding belum ditampilkan
        if (!isOnBoardingShown) {
            navController.navigate("onboarding") {
                popUpTo("splash") { inclusive = true }
            }
        }

        else {

            // kalau sudah onboarding tapi belum login
            if (userUid.isNullOrEmpty()) {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            // kalau sudah onboarding dan sudah login
            else {
                viewModel.loadUser(userUid!!)
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Base),
        contentAlignment = Alignment.Center
    ) {

        // background atas
        Image(
            painter = painterResource(id = R.drawable.bg_splash_1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.FillBounds
        )

        // background bawah
        Image(
            painter = painterResource(id = R.drawable.bg_splash_2),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.FillBounds
        )

        // logo
        Image(
            painter = painterResource(id = R.drawable.logo_splash),
            contentDescription = null,
            modifier = Modifier
                .scale(scale)
                .alpha(alpha)
                .width(200.dp)
        )
    }
}
