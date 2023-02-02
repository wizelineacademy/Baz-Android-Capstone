package com.example.baz_android_capstone.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel

@Composable
fun Principal(
    navController: NavController,
    viewModel: BookViewModel
) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "Aquí va el menú principal",
            style = MaterialTheme.typography.h4
        )
    }
}
