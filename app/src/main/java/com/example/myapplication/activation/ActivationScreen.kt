package com.example.myapplication.activation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ActivationScreen(
    viewModel: ActivationViewModel = hiltViewModel(),
    goBack: () -> Unit
) {

    val versionState by viewModel.versionState.collectAsState() // version data


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.getVersion("123") }) {
            Text("Fetch data")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ActivationScreenPreview() {
    ActivationScreen(
        goBack = {}
    )
}
