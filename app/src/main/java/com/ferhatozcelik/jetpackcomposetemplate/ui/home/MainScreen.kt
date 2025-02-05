package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (val uiState = viewModel.uiState.collectAsState().value) {
            is UiState.Loading -> {
                Text(text = "Loading...")
            }
            is UiState.Error -> {
                Text(text = "Error: ${uiState.message}")
        }
            is UiState.Success -> {
                Text(text = "Coins Loaded")
            }
        }


        Button(onClick = {
            navController.navigate(Screen.Detail.route + "/123")
        }) {
            Text(text = "Go to Detail")
        }
    }
}

