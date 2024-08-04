package com.example.myapplication.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.common.CardState
import com.example.myapplication.ui.common.ScratchCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToDetail: (CardState) -> Unit,
    onNavigateToActivation: () -> Unit,
    cardState: CardState = CardState.NOT_SCRATCHED_CARD
) {

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            ScratchCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                cardCode = if (cardState == CardState.SCRATCHED_CARD) "1234 5678 9012 3456" else "/////////////"
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onNavigateToDetail(cardState) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    Text(text = "Scratch Screen")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { onNavigateToActivation() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Activation Screen")
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToDetail = {},
        onNavigateToActivation = {}
    )
}
