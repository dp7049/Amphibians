package com.example.amphibians

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import com.example.amphibians.ui.screens.AmphibianViewModel
import com.example.amphibians.ui.screens.HomeScreen

@Composable
fun AmphibianApp(){
    val amphibianViewModel: AmphibianViewModel = viewModel(factory=AmphibianViewModel.factory)
    HomeScreen(
        uiState = amphibianViewModel.uiState
    )
}