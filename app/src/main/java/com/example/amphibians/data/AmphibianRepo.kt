package com.example.amphibians.data

import com.example.amphibians.network.AmphibianData
import com.example.amphibians.network.ApiService

interface AmphibianRepo {
    suspend fun getAmphibianData(): List<AmphibianData>
}

class NetworkAmphibianRepo(
    private val apiService: ApiService
): AmphibianRepo{
    override suspend fun getAmphibianData(): List<AmphibianData> =
        apiService.getData()

}
