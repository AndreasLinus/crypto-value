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
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.model.UiState
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinRepository
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
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
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val isUsdChecked = viewModel.isUsdSelected.collectAsState()

        Row {
            CurrencySwitch(isUsdSelected = isUsdChecked.value, onCheckedChange = viewModel::changeCurrency)
        }

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
fun CurrencySwitch(isUsdSelected: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "SEK", color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.width(8.dp))
        Switch(
            checked = isUsdSelected,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "USD", color = MaterialTheme.colorScheme.onSurface)
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


fun generateMockCryptoDataList(count: Int = 20, isUsd: Boolean = true): List<CryptoData> {
    val symbols = listOf("BTCINR", "ETHINR", "XRPINR", "LTCINR", "DOGEINR")
    val baseAssets = listOf("BTC", "ETH", "XRP", "LTC", "DOGE")
    val quoteAssets = listOf("INR")

    var currencyMultiplier = 10.0

    if (isUsd) {
        currencyMultiplier = 100.0
    }

    return List(count) {
        CryptoData(
            symbol = symbols.random(),
            baseAsset = baseAssets.random(),
            quoteAsset = quoteAssets.random(),
            openPrice = String.format(
                "%.2f",
                Random.nextDouble(10.0 * currencyMultiplier, 100.0 * currencyMultiplier)
            ),
            lowPrice = String.format(
                "%.2f",
                Random.nextDouble(50.0 * currencyMultiplier, 900.0 * currencyMultiplier)
            ),
            highPrice = String.format(
                "%.2f",
                Random.nextDouble(110.0 * currencyMultiplier, 1100.0 * currencyMultiplier)
            ),
            lastPrice = String.format(
                "%.2f",
                Random.nextDouble(80.0 * currencyMultiplier, 1000.0 * currencyMultiplier)
            ),
            volume = String.format(
                "%.2f",
                Random.nextDouble(10.0 * currencyMultiplier, 100.0 * currencyMultiplier)
            ),
            bidPrice = String.format(
                "%.2f",
                Random.nextDouble(70.0 * currencyMultiplier, 950.0 * currencyMultiplier)
            ),
            askPrice = String.format(
                "%.2f",
                Random.nextDouble(90.0 * currencyMultiplier, 1050.0 * currencyMultiplier)
            ),
            at = System.currentTimeMillis() - Random.nextLong(
                0,
                86400000
            ) // Timestamp within the last 24 hours
        )
    }




}

// Mock ViewModel for Preview
class PreviewHomeViewModel @Inject constructor(cryptoCoinRepository: CryptoCoinRepository) :
    HomeViewModel(cryptoCoinRepository) {
    private val _isUsdSelected = MutableStateFlow(true)
    override val isUsdSelected: StateFlow<Boolean> = _isUsdSelected.asStateFlow()

    /*override fun fetchCryptoCoinsList() {
        // Generate mock data
        val cryptoDataList = generateMockCryptoDataList(20)
        _uiState.value = UiState.Success(cryptoDataList) // Set success state
    }

    override fun changeCurrency(newValue: Boolean) {
        _isUsdSelected.value = newValue
    }*/
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {Surface {
        MainScreen(
            viewModel = PreviewHomeViewModel(
                PreviewCryptoCoinRepository(
                    PreviewAppApi(),
                    PreviewExampleDao()
                )
            ),
            navController = rememberNavController()
        )
    }
    }
}
