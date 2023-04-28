package com.example.sccquiz

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun First(navController: NavController, context: Context, viewModel : MainViewModel) {
    Column(modifier = Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Bottom) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color(0xFFD54DC5),
                darkIcons = false
            )
        }
        Text(text = "by" , color = Color.Gray , fontSize = 12.sp)
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = "Start Coding Club" ,color = Color(0xFFD54DC5) , fontWeight = MaterialTheme.typography.displayLarge.fontWeight )
        Spacer(modifier = Modifier.size(5.dp))

        viewModel.findUser(true)
        val searchResults = viewModel.repository.searchResults.observeAsState()
        searchResults.value?.let { viewModel.findUserByEmail(it) }
        var userName = viewModel.repository.searchResultUserName.observeAsState()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (userName.value != null) {
             navController.navigate("Home/${userName.value}")
            }else{
              navController.navigate("LoginPage")
            }
        }, 3200)
    }
}