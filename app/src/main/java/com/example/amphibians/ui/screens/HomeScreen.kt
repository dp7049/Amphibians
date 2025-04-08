package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.AmphibianData

@Composable
fun HomeScreen(
    uiState: UiState,
){
    when(uiState){
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> AmphibianGridScreen(uiState.Amphibians)
        is UiState.Error -> ErrorScreen({AmphibianViewModel::getData}, Modifier)
    }
}
@Composable
fun LoadingScreen(){
    Image(
        painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}
@Composable
fun AmphibianGridScreen(
    amphibians: List<AmphibianData>
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp)
    ) {
        items(items = amphibians){
            amphibian -> AmphibianCard(amphibian)
        }
    }
}
@Composable
fun AmphibianCard(amphibian: AmphibianData){
    Card(

    ){
        Column {
            Text(text = "$amphibian.name ($amphibian.type)")
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.amphibian_photo)
            )
        }
    }
}
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction){
            Text(stringResource(R.string.retry))
        }
    }
}