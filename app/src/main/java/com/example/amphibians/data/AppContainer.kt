package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApi

interface AppContainer {
    val amphibianRepo: AmphibianRepo
}
class DefaultAppContainer: AppContainer{
    override val amphibianRepo: AmphibianRepo by lazy {
    NetworkAmphibianRepo(AmphibianApi.retrofitService)
    }
}