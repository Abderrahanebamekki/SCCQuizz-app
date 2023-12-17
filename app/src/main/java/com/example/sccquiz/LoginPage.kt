package com.example.sccquiz

import android.content.Context
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginPage(
    context: Context,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFFD54DC5),
            darkIcons = false
        )
    }
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.size(200.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                var email = rememberSaveable {
                    mutableStateOf("")
                }
                // crate a edit text for a email
                OutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                    },
                    placeholder = {
                        Text(text = "Email")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                var password = rememberSaveable {
                    mutableStateOf("")
                }

                var visibility = remember {
                    mutableStateOf(false)
                }
                var visible = if (!visibility.value) { // icon for  appear  the password according to variable visibility
                    remember {
                        mutableStateOf(Icons.Rounded.VisibilityOff)
                    }
                } else {
                    remember {
                        mutableStateOf(Icons.Rounded.Visibility)
                    }
                }
                var showPassword = if (!visibility.value) { //for show the password according to variable visible
                    remember {
                        mutableStateOf(PasswordVisualTransformation())
                    }
                } else {
                    remember {
                        mutableStateOf(VisualTransformation.None)
                    }
                }
                // crate a edit text for a password
                OutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    placeholder = {

                        Text(text = "Password")

                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        // icon for visibility of password
                        IconButton(
                            onClick = { visibility.value = !visibility.value },
                        ) {
                            Icon(
                                imageVector = visible.value,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    },
                    visualTransformation = showPassword.value
                )
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                var username = remember {
                    mutableStateOf("")
                }
                val db = ConvertViewModel()
                db.retrieveData("timibamekki49@gmail.com",context){
                    username.value = it.toString()
                }
                GradientButton(
                    text = "Log in",
                    textColor = Color.White,
                    gradient = Brush.horizontalGradient(
                        colorStops = arrayOf(
                            Pair(0.2f , Color(0xFF6C14BC)),
                            Pair(0.7f, Color(0xFFE53DD1))
                        )
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {

                }
                // "forget password?"  for navigate to ...
                Text(
                    text = "Forget Password?",
                    modifier = Modifier
                        .clickable {

                        }
                        .padding(top = 20.dp),
                    color = Color.Black
                )
                // ------------- OR ---------------
                Row(
                    modifier = Modifier.padding(vertical = 50.dp)
                ) {
                    Divider(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(top = 5.dp),
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "OR", color = Color.Gray)
                    Spacer(modifier = Modifier.size(10.dp))
                    Divider(
                        modifier = Modifier
                            .width(120.dp)
                            .padding(top = 5.dp),
                        color = Color.Gray
                    )
                }



                // button for navigate to createAccount activity
                Button(
                    onClick = {
                        navController.navigate(route = "createAccount")
                    },
                    modifier = Modifier
                        .border(
                            brush = Brush.horizontalGradient(
                                colorStops = arrayOf(
                                    Pair(0.2f, Color(0xFF6C14BC)),
                                    Pair(0.7f, Color(0xFFE53DD1))
                                )
                            ),
                            width = 2.dp,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(text = "Create an account" , color = Color.Black)
                }

            }


        }
    }
}

@Composable
fun rememberImeState() : State<Boolean> {
    val imeState = remember {
        mutableStateOf(false)
    }
    val view = LocalView.current
    DisposableEffect(key1 = view){
        val listener = ViewTreeObserver.OnGlobalLayoutListener{
            val isKeybordOpen = ViewCompat.getRootWindowInsets(view)?.isVisible(WindowInsetsCompat.Type.ime())?:true
            imeState.value = isKeybordOpen
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return imeState
}

