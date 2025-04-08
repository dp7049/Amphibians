package com.example.amphibians.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibianRepo
import com.example.amphibians.network.AmphibianData
import kotlinx.coroutines.launch

sealed interface UiState{
    data class Success(val Amphibians: List<AmphibianData>): UiState
    object Loading: UiState
    object Error: UiState
}

class AmphibianViewModel(private val amphibianRepo: AmphibianRepo): ViewModel(){
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getData()
    }
    fun getData(){
        viewModelScope.launch{
            try{
                val result: List<AmphibianData> = amphibianRepo.getAmphibianData()
                Log.e("TAG", "getData: $result", )
               uiState =  UiState.Success(result)
            }
            catch (e: Exception){
                Log.d("TAG", "getData: $e")
                uiState = UiState.Error

            }
        }
    }
    companion object{
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibianRepo = application.container.amphibianRepo
                AmphibianViewModel(amphibianRepo)
            }
        }
    }
}