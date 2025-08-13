package com.example.weaveon.domain.model

import androidx.compose.ui.graphics.Color
import com.example.weaveon.R
import com.example.weaveon.presentation.ui.theme.Secondary09

sealed class BottomBarItem(
    val title: String,
    val iconRes: Int,
    val selectedColor: Color = Secondary09,
    val unselectedColor: Color = Color(0xFFFEFEFE)
) {
    data object Home : BottomBarItem("Home", R.drawable.ic_home)
    data object Aibu : BottomBarItem("Aibu", R.drawable.ic_chatbot)
    data object Profile : BottomBarItem("Profile", R.drawable.ic_user)
}