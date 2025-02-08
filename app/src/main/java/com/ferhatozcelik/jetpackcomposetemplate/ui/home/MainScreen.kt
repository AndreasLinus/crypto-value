package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.model.UiState
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import kotlin.random.Random


// TODO: add option for big and small views
// TODO: add change currency bar option
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {

    /*MaterialTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CryptoListScreen()
        }
    }*/

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 16.dp),
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
                CryptoListScreen(cryptoList = uiState.cryptoDataList)
            }
        }


        Button(onClick = {
            navController.navigate(Screen.Detail.route + "/123")
        }) {
            Text(text = "Go to Detail")
        }
    }
}

@Composable
fun CryptoItem(crypto: CryptoData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = crypto.symbol,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                val lastPriceColor =
                    if (crypto.lastPrice.toDouble() > crypto.openPrice.toDouble()) {
                        Color.Green
                    } else if (crypto.lastPrice.toDouble() < crypto.openPrice.toDouble()) {
                        Color.Red
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                Text(
                    text = crypto.lastPrice,
                    style = MaterialTheme.typography.bodyLarge,
                    color = lastPriceColor
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Base: ${crypto.baseAsset}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Text(
                    text = "Quote: ${crypto.quoteAsset}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "High: ${crypto.highPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Green.copy(alpha = 0.7f)
                )
                Text(
                    text = "Low: ${crypto.lowPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Red.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bid: ${crypto.bidPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Text(
                    text = "Ask: ${crypto.askPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Volume: ${crypto.volume}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CryptoListScreen(cryptoList: List<CryptoData>) {
    //val cryptoList = generateMockCryptoDataList(20) // Generate 20 mock items

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(cryptoList) { crypto ->
            CryptoItem(crypto)
        }
    }
}


fun generateMockCryptoDataList(count: Int): List<CryptoData> {
    val symbols = listOf("BTCINR", "ETHINR", "XRPINR", "LTCINR", "DOGEINR")
    val baseAssets = listOf("BTC", "ETH", "XRP", "LTC", "DOGE")
    val quoteAssets = listOf("INR")

    return List(count) {
        CryptoData(
            symbol = symbols.random(),
            baseAsset = baseAssets.random(),
            quoteAsset = quoteAssets.random(),
            openPrice = String.format("%.2f", Random.nextDouble(1000.0, 100000.0)),
            lowPrice = String.format("%.2f", Random.nextDouble(500.0, 90000.0)),
            highPrice = String.format("%.2f", Random.nextDouble(1100.0, 110000.0)),
            lastPrice = String.format("%.2f", Random.nextDouble(800.0, 100000.0)),
            volume = String.format("%.2f", Random.nextDouble(0.0, 1000.0)),
            bidPrice = String.format("%.2f", Random.nextDouble(700.0, 95000.0)),
            askPrice = String.format("%.2f", Random.nextDouble(900.0, 105000.0)),
            at = System.currentTimeMillis() - Random.nextLong(
                0,
                86400000
            ) // Timestamp within the last 24 hours
        )
    }
}
