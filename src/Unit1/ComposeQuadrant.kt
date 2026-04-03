package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme
import kotlin.io.encoding.Base64



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    ComposeQuadrant(
                        arrayOf(
                            stringResource(R.string.quadrant11_title),
                            stringResource(R.string.quadrant12_title),
                            stringResource(R.string.quadrant21_title),
                            stringResource(R.string.quadrant22_title)
                        ),
                        arrayOf(
                            stringResource(R.string.quadrant11_content),
                            stringResource(R.string.quadrant12_content),
                            stringResource(R.string.quadrant21_content),
                            stringResource(R.string.quadrant22_content)
                        ),
                        arrayOf(
                            colorResource(R.color.quadrant11),
                            colorResource(R.color.quadrant12),
                            colorResource(R.color.quadrant21),
                            colorResource(R.color.quadrant22)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Quadrant(title: String, content: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Black
            )
            Text(
                text = content,
                textAlign = TextAlign.Justify,
                color = Color.Black
            )
        }
    }
}

@Composable
fun QuadrantRow(titles: Array<String>, contents: Array<String>,
                colors: Array<Color>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Quadrant(titles[0], contents[0], colors[0], Modifier.weight(1f))
        Quadrant(titles[1], contents[1], colors[1], Modifier.weight(1f))
    }
}

@Composable
fun ComposeQuadrant(titles: Array<String>, contents: Array<String>,
                    colors: Array<Color>, modifier: Modifier = Modifier) {
    Column {
        QuadrantRow(
            arrayOf(titles[0], titles[1]),
            arrayOf(contents[0], contents[1]),
            arrayOf(colors[0], colors[1]),
            Modifier.weight(1f)
        )
        QuadrantRow(
            arrayOf(titles[2], titles[3]),
            arrayOf(contents[2], contents[3]),
            arrayOf(colors[2], colors[3]),
            Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuadrantPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrant(
            arrayOf(
                stringResource(R.string.quadrant11_title),
                stringResource(R.string.quadrant12_title),
                stringResource(R.string.quadrant21_title),
                stringResource(R.string.quadrant22_title)
            ),
            arrayOf(
                stringResource(R.string.quadrant11_content),
                stringResource(R.string.quadrant12_content),
                stringResource(R.string.quadrant21_content),
                stringResource(R.string.quadrant22_content)
            ),
            arrayOf(
                colorResource(R.color.quadrant11),
                colorResource(R.color.quadrant12),
                colorResource(R.color.quadrant21),
                colorResource(R.color.quadrant22)
            )
        )
    }
}
