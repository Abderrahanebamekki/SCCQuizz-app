package com.example.sccquiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun optionsQuestion(
   navigateViewModel: QuestionsViewModel
){
Quizes(navigateViewModel)
}
@Composable
fun Quizes(navigateViewModel: QuestionsViewModel){
    val questions = navigateViewModel.selectedListQuestions
 LazyColumn{
    items(questions.value!![0]?.listOption!!){
        chooseOptions(
            option = it!! ,
            answer = questions.value!![0]?.answer!!
        ) {
              
        }
    }
 }
}


@Composable
fun chooseOptions(
    option: Option,
    answer : String,
    onClick : () ->Unit
) {

        Surface(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(color = Color.Red.copy(0.3f), shape = RoundedCornerShape(20.dp))
                .clickable {
                    onClick()
                }
                .height(50.dp),
            color = Color.White,
            border = BorderStroke(2.dp, Color.DarkGray)
        ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = option.text!!)
                }
            }
 }
