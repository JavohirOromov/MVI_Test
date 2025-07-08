import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mvitest.intent.DashboardIntent
import com.example.mvitest.state.DashboardState
import com.example.mvitest.viewmodel.DashboardViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

/**
 * Creator: Javohir Oromov
 * Date: 08/07/25
 * Project: MVI Test
 * Javohir's MacBook Air
 */
@Composable
fun DashboardScreen(viewModel: DashboardViewModel){
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    val isRefreshing = state is DashboardState.Loading

    LaunchedEffect(Unit) {
        viewModel.userIntent.send(DashboardIntent.FetchBook)
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            scope.launch {
                viewModel.userIntent.send(DashboardIntent.FetchBook)
            }
        }
    ) {
        when(state){
            is DashboardState.Idle -> Text("Idle")
            is DashboardState.Loading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
            is DashboardState.Success ->{
                val books = (state as DashboardState.Success).books

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)

                ) {
                    items(books){ book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = book.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = book.body,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
            is DashboardState.Error -> Text(
                "Error: ${(state as DashboardState.Error).error}",
                modifier = Modifier.fillMaxSize().padding(top = 100.dp),
                textAlign = TextAlign.Center
            )
        }
    }

}