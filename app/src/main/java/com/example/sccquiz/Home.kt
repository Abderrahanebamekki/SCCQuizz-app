package com.example.sccquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.LockClock
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun Home(
    userName : String = "username" ,
    navController: NavHostController ,
    navigateViewModel: QuestionsObjectViewModel
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFFD54DC5),
            darkIcons = false
        )
    }

    var username = remember {
        mutableStateOf(userName)
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colorStops = arrayOf(
                            Pair(0.2f, Color(0xFF6C14BC)),
                            Pair(0.7f, Color(0xFFE53DD1))
                        )
                    )

                )
                .height(280.dp)
                .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            UserInformation()
            Spacer(modifier = Modifier.size(22.dp))
            Text(text = "hello ,${username.value}!" , color = White , modifier = Modifier.padding(start = 5.dp))
            Spacer(modifier = Modifier.size(15.dp))
            Text(text = " Let's test your knowledge " , fontSize = 20.sp , fontWeight = FontWeight.Bold , color = White )
            Spacer(modifier = Modifier.size(15.dp))
            search()
        }
        Column(
            Modifier
                .padding(top = 250.dp)
                .background(
                    color = White,
                    shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
                )
                .fillMaxSize()
        ) {
            val v = QuizViewModel()
            saveData(viewModels = v , navController = navController , navigateViewModel = navigateViewModel)
            
        }



    }
}

@Composable
fun saveData(viewModels: QuizViewModel , navController : NavHostController , navigateViewModel: QuestionsObjectViewModel) {
    when(val result = viewModels.response.value){
        is DataState.Loading ->{
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator(color = Color(0xFFE53DD1))
            }
        }
        is DataState.Success -> {
            ShowLazyList(questions = result.data , navController = navController , navigateViewModel = navigateViewModel)
        }
        is DataState.Failure -> {
            Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                )
            }
        }
        else ->{
            Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
                Text(
                    text = "no result",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                )
            }
        }
    }
}



@Composable
fun ShowLazyList(questions: MutableList<Quiz> , navController : NavHostController , navigateViewModel: QuestionsObjectViewModel) {
    LazyColumn(
        modifier = Modifier.padding(top = 50.dp)
    ) {
        items(questions){
            CardView(it){
                navigateViewModel.selectObject(it.listQuestions)
                navController.navigate("optionsQuestion")
            }
        }
    }
}

@Composable
fun CardView(
    questions: Quiz,
    onClick : () -> Unit
) {
   Card(
       modifier = Modifier
           .padding(horizontal = 12.dp, vertical = 8.dp)
           .fillMaxWidth()
           .height(150.dp)
           .border(2.dp, Color(0xFFE53DD1), RoundedCornerShape(40.dp))
           .background(Gray, RoundedCornerShape(40.dp))
           .clickable {
                      onClick()
           },
       colors = CardDefaults.cardColors(
           containerColor = Gray,
       ),
       shape = RoundedCornerShape(40.dp)
   ) {
     Row(
         verticalAlignment = Alignment.CenterVertically ,
         modifier = Modifier.padding(top = 15.dp , start = 10.dp , end = 10.dp)
     ) {
         AsyncImage(
             model = ImageRequest
                 .Builder(LocalContext.current)
                 .data(questions.img)
                 .crossfade(true)
                 .build(),
             contentDescription = null,
             modifier = Modifier
                 .size(120.dp)
                 .clip(RoundedCornerShape(30.dp)),
             contentScale = ContentScale.Crop,
         )
         Spacer(modifier = Modifier.size(8.dp) )
         Column() {
             Text(text = questions.nq!!.toString() , fontWeight = FontWeight.Bold , color = Color(0xFFE53DD1) , fontSize = 20.sp)
             Spacer(modifier = Modifier.size(5.dp))
             textIcon(icon = Icons.Rounded.Quiz, text = questions.nbq!!.toString() , color = Black )
             Spacer(modifier = Modifier.size(5.dp))
             textIcon(icon = Icons.Rounded.LockClock, text = questions.tq!!.toString(), color = Black )
             Spacer(modifier = Modifier.size(5.dp))
         }
         Spacer(modifier = Modifier.weight(1f))
         textIcon(icon = Icons.Rounded.Star, text = questions.rq.toString() , color = Color(0xFFE53DD1) , Color.Yellow)
     }
       
       
   }
}

@Composable
fun textIcon(
    icon : ImageVector ,
    text :  String ,
    color :  Color ,
    tint : Color = Black
) {
    Row() {
        Icon(imageVector = icon, contentDescription = "" , tint = tint)
        Spacer(modifier = Modifier.size(1.dp))
        Text(text = text , color = color)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun search() {
    OutlinedTextField(
        value = "",
        onValueChange = {  } ,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(30.dp)),
        placeholder = {
            Text(
                text = "Search here...",
                modifier = Modifier
                    .alpha(12f),
                color = Gray
            )
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        ),
        singleLine = true ,
        leadingIcon = {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .alpha(12f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color(0xFF5AA2B8)
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .alpha(12f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close Icon",
                    tint = Color(0xFF5AA2B8)
                )
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = {

            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            cursorColor = Black,
            textColor = Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(30.dp)
    )
}


@Composable
fun UserInformation() {
    Row() {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Settings" , Modifier.size(80.dp) )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {

        }) {
            Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "" , Modifier.size(80.dp) )
        }

    }
}

val q = Quiz("qura'an" ,2,2,2f)


