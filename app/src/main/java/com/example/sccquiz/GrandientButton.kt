package com.example.sccquiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientButton(
    text : String,
    textColor: Color,
    gradient : Brush,
    modifier : Modifier = Modifier,
    onClick : () -> Unit
) {
    Button(
        onClick = {onClick()} ,
        colors = ButtonDefaults.buttonColors(
            containerColor =  Color.Transparent
        ) ,
        modifier= Modifier
            .then(modifier)
            .padding(horizontal = 50.dp)
            .height(50.dp),
        border = BorderStroke(0.dp , color = Color.Transparent) ,
        contentPadding = PaddingValues(vertical = 5.dp , horizontal = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .background(brush = gradient, shape = RoundedCornerShape(18.dp))
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ){
            Text(text = text , color = textColor)
        }
    }
}