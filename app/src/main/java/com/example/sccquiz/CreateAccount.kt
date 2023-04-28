package com.example.sccquiz

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Facebook
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createAccount(
    navController : NavController ,
    viewModel : MainViewModel
) {
    var context : Context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val imeState = rememberImeState()
        val scrollState = rememberScrollState()

        LaunchedEffect(key1 = imeState.value) {
            if (imeState.value) {
                scrollState.scrollTo(scrollState.maxValue)
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.size(80.dp))
            Card(
                modifier = Modifier.size(100.dp)
            ) {

            }
            Spacer(modifier = Modifier.size(80.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // username
                var username  = rememberSaveable {
                    mutableStateOf("")
                }
                // crate a edit text for a email
                OutlinedTextField(
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                    placeholder = {
                        Text(text = "username")
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
                        Text(text = "Phone or Email")
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
                var password  = rememberSaveable {
                    mutableStateOf("")
                }
                // crate a edit text for a password
                var visibility = remember {
                    mutableStateOf(false)
                }
                var visible = if (!visibility.value) {
                    remember {
                        mutableStateOf(Icons.Rounded.VisibilityOff)
                    }
                } else {
                    remember {
                        mutableStateOf(Icons.Rounded.Visibility)
                    }
                }
                var showPassword = if (!visibility.value) {
                    remember {
                        mutableStateOf(PasswordVisualTransformation())
                    }
                } else {
                    remember {
                        mutableStateOf(VisualTransformation.None)
                    }
                }
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
                        IconButton(
                            onClick = { visibility.value = !visibility.value },
                        ) {
                            Icon(
                                imageVector = visible.value,
                                contentDescription = "",
                                tint = Color.Black
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

                GradientButton(
                    text = "Create an account",
                    textColor = Color.White,
                    gradient = Brush.horizontalGradient(
                        colorStops = arrayOf(
                            Pair(0.2f , Color(0xFF6C14BC)),
                            Pair(0.7f, Color(0xFFE53DD1))
                        )
                    )
                ) {
                    if (email.value.length > 5 && password.value.length>5 && username.value.length > 5) {
                        val user = User(
                            email = email.value.trim(),
                            password = password.value.trim(),
                            userName = username.value.trim() ,
                            isLastUser = true
                        )
                        user.id = 1
                        //viewModel.insertUser(user)
                        user.register(context = context , navController = navController)
                    }else{
                        Toast.makeText(context , "please fill the text" , Toast.LENGTH_LONG).show()
                    }
                }
                Spacer(modifier = Modifier.size(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Already have one? " , color = Color.Black)
                    Text(
                        text = "login here?" ,
                        color = Color.Black,
                        modifier = Modifier
                            .clickable {

                            }
                    )
                }


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
                Spacer(modifier = Modifier.size(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    IconRegister(icon = Icons.Rounded.Email, description ="Email" , tint = Red ){

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconRegister(icon = Icons.Rounded.Facebook, description ="FaceBook" , tint = Blue){}
                    Spacer(modifier = Modifier.weight(1f))
                }

            }


        }
    }
}

@Composable
fun IconRegister(
    icon : ImageVector,
    description  : String ,
    tint : Color,
    onClick : ()-> Unit
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = icon  ,
            contentDescription = description ,
            tint = tint ,
            modifier = Modifier.size(200.dp)
        )
    }
}

