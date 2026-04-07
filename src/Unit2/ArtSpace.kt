package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

data class Artwork(
    @DrawableRes val imageResId: Int,
    @StringRes val descriptionResId: Int,
    @StringRes val titleResId: Int,
    @StringRes val artistResId: Int,
    @StringRes val yearResId: Int
)

@Composable
fun ArtworkInfo(
    @DrawableRes wall: Int,
    @StringRes description: Int,
    @StringRes title: Int,
    @StringRes artist: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(wall),
        contentDescription = stringResource(description),

    )
    Spacer(modifier = Modifier.padding(16.dp))
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "\"${stringResource(title)}\"",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row {
            Text(text = stringResource(artist),
                style = MaterialTheme.typography.titleMedium)
            Text(text = " (${stringResource(year)})")
        }
    }
}

@Composable
fun DisplayButton(
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    reverse: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = Modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
        ) {
            if (reverse) {
                Text(
                    text = text,
                    modifier = modifier
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null
                )
            } else {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = text,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }

    val artworkList = listOf(
        Artwork(R.drawable.artwork_1, R.string.description_1, R.string.title_1, R.string.artist_1, R.string.year_1),
        Artwork(R.drawable.artwork_2, R.string.description_2, R.string.title_2, R.string.artist_2, R.string.year_2),
        Artwork(R.drawable.artwork_3, R.string.description_3, R.string.title_3, R.string.artist_3, R.string.year_3),
        Artwork(R.drawable.artwork_4, R.string.description_4, R.string.title_4, R.string.artist_4, R.string.year_4),
        Artwork(R.drawable.artwork_5, R.string.description_5, R.string.title_5, R.string.artist_5, R.string.year_5)
    )

    var currentArtwork = artworkList[currentIndex]

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtworkInfo(
            wall = currentArtwork.imageResId,
            description = currentArtwork.descriptionResId,
            title = currentArtwork.titleResId,
            artist = currentArtwork.artistResId,
            year = currentArtwork.yearResId
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .wrapContentHeight(align = Alignment.Bottom)
        ) {
            if (currentIndex == 0) {
                DisplayButton(
                    icon = R.drawable.arrow_forward,
                    text = stringResource(R.string.next),
                    onClick = { if (currentIndex < artworkList.size - 1) currentIndex++ },
                    reverse = true
                )
            } else if (currentIndex == artworkList.size - 1) {
                DisplayButton(
                    icon = R.drawable.arrow_back,
                    text = stringResource(R.string.previous),
                    onClick = { if (currentIndex > 0) currentIndex-- }
                )
            } else {
                DisplayButton(
                    icon = R.drawable.arrow_back,
                    text = stringResource(R.string.previous),
                    onClick = { if (currentIndex > 0) currentIndex-- }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                DisplayButton(
                    icon = R.drawable.arrow_forward,
                    text = stringResource(R.string.next),
                    onClick = { if (currentIndex < artworkList.size - 1) currentIndex++ },
                    reverse = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp(
            Modifier.fillMaxSize()
        )
    }
}