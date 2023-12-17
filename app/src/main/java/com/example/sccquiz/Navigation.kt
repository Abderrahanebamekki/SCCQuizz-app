package com.example.sccquiz

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(context : Context, viewModel : MainViewModel  ) {
    val navController = rememberNavController()
    val navigateViewModel = QuestionsViewModel()
    NavHost(navController = navController , startDestination = "LoginPage"){
        composable(route = "First"){
            First(navController = navController , context , viewModel)
        }
        composable(route = "LoginPage"){
            loginPage(context = context , navController)
        }
        composable(route = "createAccount"){
            createAccount(navController = navController , viewModel)
        }
        composable(
            "Home/{userName}" ,
            arguments = listOf(
                navArgument(name = "userName"){
                    type = NavType.StringType
                }
            )
        ){
            it.arguments?.getString("userName")?.let { it1 ->
                Home( userName = it1  , navController , navigateViewModel)
            }
        }
        composable(route = "optionsQuestion"){
            optionsQuestion(  )
        }
    }
}