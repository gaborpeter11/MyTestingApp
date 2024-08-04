package com.example.myapplication

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.common.CardState
import com.example.myapplication.ui.common.FullScreenLoading
import com.example.myapplication.ui.common.ScratchCard

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    goBack: (CardState) -> Unit,
    cardState: CardState
) {

    val isLoading = viewModel.loading.value
    val scratchCardCode = viewModel.scratchCardCode.value
    val scratchCard = viewModel.scratchCard.value
    val cardStateVal = if (scratchCard) CardState.SCRATCHED_CARD else CardState.NOT_SCRATCHED_CARD

    BackHandler {
        viewModel.onBackClicked()
        goBack(cardStateVal)
    }

    LaunchedEffect(Unit) {
        if (cardState == CardState.SCRATCHED_CARD) {
            viewModel.scratchCard(true)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Main content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ScratchCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                cardCode = if (!scratchCard) "////////////////" else scratchCardCode
            )

            Button(onClick = { viewModel.scratchCard() }) {
                Text(text = if (!scratchCard) "Scratch Card" else "Card already scratched")
            }
        }

        FullScreenLoading(isLoading = isLoading)
    }

}


@Preview(showBackground = true)
@Composable
fun DetailScreenNotScratchedPreview() {
    DetailScreen(goBack = {}, cardState = CardState.NOT_SCRATCHED_CARD)
}

@Preview(showBackground = true)
@Composable
fun DetailScreenScratchedPreview() {
    DetailScreen(goBack = {}, cardState = CardState.SCRATCHED_CARD)
}
