package com.example.customprogressbarjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customprogressbarjetpackcompose.model.ColorItemModel
import com.example.customprogressbarjetpackcompose.ui.theme.CustomProgressBarJetpackComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomProgressBarJetpackComposeTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    //Maybe you need to change content Alignment
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        CustomProgressBar(
                            modifier = Modifier
                                .clip(RoundedCornerShape(18.dp)),
                            height = 40.dp,
                            backgroundColor = Color.Gray,
                            progress = 88
                        )

                        ChangeColorSection(
                            listOf(
                                ColorItemModel("Default", Color.Blue,
                                    Brush.horizontalGradient(
                                    listOf(
                                        Color.Blue,
                                        Color.Green
                                    )
                                ) ,true),
                                ColorItemModel("Red", Color.Red,Brush.horizontalGradient(
                                    listOf(
                                        Color.Red,
                                        Color.Blue
                                    )
                                ) ,false),
                                ColorItemModel("Blue", Color.Cyan, Brush.horizontalGradient(
                                    listOf(
                                        Color.Cyan,
                                        Color.Green
                                    )
                                ),false),
                                ColorItemModel("Black", Color.Black, Brush.horizontalGradient(
                                    listOf(
                                        Color.Black,
                                        Color.Green
                                    )
                                ),false),
                                ColorItemModel("Yellow", Color.Yellow, Brush.horizontalGradient(
                                    listOf(
                                        Color.Yellow,
                                        Color.Green
                                    )
                                ),false),
                            )
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun ChangeColorSection(colorItems: List<ColorItemModel>, viewModel: MainViewModel = viewModel()) {
    var selectedItemIndex by remember {
        mutableStateOf(0)
    }
    LazyRow(Modifier.padding(top = 12.dp)) {
        items(colorItems.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        selectedItemIndex = it
                        viewModel.onColorChanged(colorItems[it].brush)
                    }
            ) {
                Canvas(
                    modifier = Modifier
                        .size(24.dp)
                        .border(
                            if (it == selectedItemIndex) BorderStroke(
                                0.dp,
                                colorItems[it].color
                            ) else BorderStroke(4.5.dp, colorItems[it].color), shape = CircleShape
                        ),
                    onDraw = {
                        drawCircle(
                            color = if (it == selectedItemIndex) colorItems[it].color
                            else Color.Transparent
                        )
                    })
                Text(
                    text = colorItems[it].description,
                    style = MaterialTheme.typography.body2,
                    color = colorItems[it].color
                )
            }
        }
    }
}

@Composable
fun CustomProgressBar(
    modifier: Modifier,
    height: Dp,
    backgroundColor: Color,
    progressColorGradient: Brush = Brush.horizontalGradient(
        listOf(
            Color.Blue,
            Color.Green
        )
    ),
    progress: Int,
    viewModel: MainViewModel = viewModel()
) {
    val brush by viewModel.colorLiveData.observeAsState(initial = Brush.horizontalGradient(
        listOf(
            Color.Blue,
            Color.Green
        )
    ))
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
                    .background(brush = brush)
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
            progressColorGradient = Brush.horizontalGradient(listOf(Color.Blue, Color.Green)),
            progress = 99
        )
    }
}