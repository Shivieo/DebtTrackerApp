package com.example.debttrackerapp.total_debt_simple

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.debttrackerapp.news.Article

@Composable
fun HomeScreenDebt(navController: NavController, viewModel: DebtViewModel) {
    // Observe the news articles from the viewModel
    val articles by viewModel.newsArticles.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(40.dp))
        Text("Debt Tracker Dashboard", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(30.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // Navigation to basic debt tracker
                navController.navigate(Routes.BasicDebt)
            }
        ) {
            Text("Basic Debt Tracker")
        }

        Spacer(Modifier.height(15.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // Navigation to Advance debt tracker
                navController.navigate(Routes.AdvanceDebt)
            }
        ) {
            Text("Advance Debt Tracker")
        }

        Spacer(Modifier.height(40.dp))

        // The News Section
        Text(
            text = "Stock Market News",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(Modifier.height(10.dp))

        if (articles.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.padding(20.dp))
            Text("Fetching latest financial news...")
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(articles) { article ->
                    NewsCard(article = article) {
                        // Open the full article in the browser when clicked
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(article: Article, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(200.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = article.description ?: "Click to read more...",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
