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
import androidx.navigation.compose.rememberNavController
import com.ferhatozcelik.jetpackcomposetemplate.data.model.CryptoData
import com.ferhatozcelik.jetpackcomposetemplate.data.model.UiState
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CryptoCoinValueRepository
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.CurrencyConversionRepository
import com.ferhatozcelik.jetpackcomposetemplate.navigation.Screen
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.components.CatSwitch
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.components.LoadingScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.theme.LightDarkModePreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val isUsdChecked = viewModel.isUsdSelected.collectAsState()

        when (val uiState = viewModel.uiState.collectAsState().value) {
            is UiState.Loading -> {
                LoadingScreen()
            }

            is UiState.Error -> {
                Text(text = "Error: ${uiState.message}")
            }

            is UiState.Success -> {
                CatSwitch(
                    checked = isUsdChecked.value,
                    onCheckedChange = viewModel::changeCurrency
                )
                CryptoList(cryptoList = uiState.cryptoDataList)
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
fun CryptoList(cryptoList: List<CryptoData>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(cryptoList) { crypto ->
            CryptoItem(crypto)
        }
    }
}

class PreviewHomeViewModel @Inject constructor(
    cryptoCoinValueRepository: CryptoCoinValueRepository,
    conversionRepository: CurrencyConversionRepository,
    userPreferencesDao: PreviewUserPreferencesDao
) :
    HomeViewModel(
        cryptoCoinValueRepository = cryptoCoinValueRepository,
        currencyConversionRepository = conversionRepository,
        userPreferencesDao = userPreferencesDao
    ) {
    private val _isUsdSelected = MutableStateFlow(true)
    override val isUsdSelected: StateFlow<Boolean> = _isUsdSelected.asStateFlow()

}

@LightDarkModePreview
@Composable
fun MainScreenPreviewLoading() {
    MaterialTheme {
        MainScreen(
            viewModel = PreviewHomeViewModel(
                cryptoCoinValueRepository = PreviewCryptoCoinValueRepository(cryptoCoinApi = PreviewCryptoCoinValueApi()),
                conversionRepository = PreviewCurrencyConversionRepository(
                    previewCurrencyConversionApi = PreviewCurrencyConversionApi()
                ),
                userPreferencesDao = PreviewUserPreferencesDao()
            ),
            navController = rememberNavController()
        )
    }
}