package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeStep(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    val currentResourcesIds = when (result) {
        1 -> arrayOf(R.drawable.lemon_tree, R.string.tree_description, R.string.lemon_tree)
        in 2..5 -> arrayOf(R.drawable.lemon_squeeze, R.string.lemon_description, R.string.lemon)
        6 -> arrayOf(R.drawable.lemon_drink, R.string.drink_description, R.string.glass_of_lemonade)
        else -> arrayOf(R.drawable.lemon_restart, R.string.empty_glass_description, R.string.empty_glass)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row (
            modifier = Modifier
                .background(Color(0xFFF9E44C))
                .padding(top = 48.dp, bottom = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonColors(
                containerColor = Color(243, 251, 229),
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black
            ),
            border = BorderStroke(2.dp, Color(105, 205, 216)),
            onClick = {
            result = (result + 1) % 7
            if (result == 2) {
                result = (2..5).random()
            }
        } ) {
            Image(
                painter = painterResource(currentResourcesIds[0]),
                contentDescription = stringResource(currentResourcesIds[1])
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(currentResourcesIds[2]),
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(2f))
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    LemonadeStep(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    )
}