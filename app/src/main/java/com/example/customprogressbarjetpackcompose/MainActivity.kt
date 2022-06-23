package com.example.customprogressbarjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customprogressbarjetpackcompose.ui.theme.CustomProgressBarJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomProgressBarJetpackComposeTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomProgressBar(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp)),
                        height = 40.dp,
                        backgroundColor = Color.Gray,
                        progressColor = Brush.horizontalGradient(listOf(Color.Blue, Color.Green)),
                        progress = 88
                    )
                }
            }
        }
    }
}

@Composable
fun CustomProgressBar(
    modifier: Modifier,
    height: Dp,
    backgroundColor: Color,
    progressColor: Brush,
    progress: Int
) {
    Box(modifier = Modifier.padding(horizontal = 24.dp)) {
        BoxWithConstraints(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .height(height)

        ) {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .background(progressColor)
                    .width(maxWidth * progress / 100),
            )
            Text(
                text = "$progress %",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 400)
@Composable
fun DefaultPreview() {
    CustomProgressBarJetpackComposeTheme {
        CustomProgressBar(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp)),
            height = 30.dp,
            backgroundColor = Color.Gray,
            progressColor = Brush.horizontalGradient(listOf(Color.Blue, Color.Green)),
            progress = 99
        )
    }
}