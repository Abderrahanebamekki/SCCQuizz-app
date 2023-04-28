package com.example.sccquiz

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Co2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.rpc.LocalizedMessage


@Composable
fun optionsQuestion(
//    navigateViewModel: QuestionsObjectViewModel
){
  Quiz()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Quiz() {
    val handler = Handler(Looper.getMainLooper())
    Column {
        val questions = Question(
            question = "jdfslfmm",
            listOption = listOf(
                Option("fdddd"),
                Option("sefds"),
                Option("fdgagdf")
            ),
            answer = "dsfde"
        )

        val questions1 = Question(
            question = "jdpfrfoepfoeo",
            listOption = listOf(
                Option("fdddd"),
                Option("sefds"),
                Option("fdgagdf")
            ),
            answer = "dsfde"
        )

        var index = remember {
            mutableStateOf(0)
        }

        var visible = remember {
            mutableStateOf(true)
        }

        var visible1 = remember {
            mutableStateOf(true)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally ,
            modifier = Modifier.height(350.dp)
        ) {
            //val questions = navigateViewModel.selectedListQuestions // list of question navigate from home activity
 
            var ques = listOf(questions, questions1)
            AnimatedVisibility(
                visible = visible.value,
                enter = scaleIn(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = scaleOut(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                Text(
                    text = ques[index.value].question!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                    //    questions.value!![index.value]?.question!!
                )
            }
            LazyColumn {
                items(
                    questions.listOption!!
                    //    questions.value!![index.value]!!.listOption!!
                ) {
                    var correctAnswer = remember {
                        mutableStateOf(true)
                    }
                    var icon =
                       if (correctAnswer.value){
                           remember {
                               mutableStateOf(Icons.Rounded.Check)
                           }
                       }else{
                           remember {
                               mutableStateOf(Icons.Rounded.Close)
                           }
                       }
                    var showIcon = remember {
                        mutableStateOf(false)
                    }
                    var point = remember {
                        mutableStateOf(0)
                    }
                    val option = it
                    if (option != null)
                        chooseOption(
                            option = option,
                            visible1 = visible1.value ,
                            icon = icon.value ,
                            showIcon = false
                        ){
                            showIcon.value = true
                            val answer = questions.answer == option.text
                            if (answer){
                                point.value+=1
                            }
                        }
                }
            }

        }
            Spacer(modifier = Modifier.size(40.dp))
        Row{
            Spacer(modifier = Modifier.weight(1f))
            GradientButton(
                text = "Next",
                textColor = Color.White ,
                gradient = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        Pair(0.2f , Color(0xFF6C14BC)),
                        Pair(0.7f, Color(0xFFE53DD1))
                    )
                ),
                modifier = Modifier.width(220.dp)
            ) {
                visible.value = !visible.value
                visible1.value = !visible1.value
                handler.postDelayed({
                    index.value = 1
                    visible.value = !visible.value
                    visible1.value = !visible1.value
                }, 1500)
            }
        }
    }

}

@Composable
fun chooseOption(
    option: Option ,
    visible1 : Boolean,
    icon : ImageVector,
    showIcon : Boolean,
    onClick : () ->Unit
    ) {
    AnimatedVisibility(
        visible = visible1,
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = 1500
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = 1500
            )
        )
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(visible = showIcon) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .size(38.dp),
                        tint = if (icon == Icons.Rounded.Close) Color.Red else Color.Green
                    )
                }

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
    }
}
