package com.example.businesscard

import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BusinessCard(
                        colorResource(R.color.android_background),
                        painterResource(R.drawable.android_logo),
                        arrayOf(stringResource(R.string.name), stringResource(R.string.job)),
                        arrayOf(
                            stringResource(R.string.phone),
                            stringResource(R.string.location),
                            stringResource(R.string.github),
                            stringResource(R.string.email))
                    )
                }
            }
        }
    }
}

@Composable
fun PersonalInfo(image: Painter, name: String, job: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = modifier
                .width(136.dp)
                .background(colorResource(R.color.android_logo))
                .padding(12.dp)
        )
        Text(
            text = name,
            modifier = modifier
                .padding(top = 12.dp, bottom = 4.dp),
            fontSize = 48.sp,
            color = Color.Black
        )
        Text(
            text = job,
            modifier = modifier,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.android_text)
        )
    }
}

@Composable
fun ContactInfo(
    icons: Array<ImageVector>,
    descriptions: Array<String>,
    color: Color,
    data: Array<String>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.padding(bottom = 48.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RowInfo(icons[0], descriptions[0], color, data[0])
        RowInfo(icons[1], descriptions[1], color, data[1])
        RowInfo(icons[2], descriptions[2], color, data[2])
        RowInfo(icons[3], descriptions[3], color, data[3])
    }
}

@Composable
fun RowInfo(
    icon: ImageVector,
    description: String,
    color: Color,
    text: String,
    modifier: Modifier = Modifier) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = color,
            modifier = modifier
        )
        Text(
            text = text,
            modifier = modifier,
            color = Color.Black
        )
    }
}

@Composable
fun BusinessCard(
    color: Color,
    image: Painter,
    personalData: Array<String>,
    contactData: Array<String>,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        PersonalInfo(image, personalData[0], personalData[1])
        Spacer(modifier = Modifier.weight(1f))
        ContactInfo(
            arrayOf(
                Icons.Rounded.Phone,
                Icons.Rounded.Place,
                Icons.Rounded.FavoriteBorder,
                Icons.Rounded.MailOutline
            ),
            arrayOf(
                stringResource(R.string.phone_description),
                stringResource(R.string.location_description),
                stringResource(R.string.github_description),
                stringResource(R.string.email_description)
            ),
            colorResource(R.color.android_text),
            contactData
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(
            colorResource(R.color.android_background),
            painterResource(R.drawable.android_logo),
            arrayOf(stringResource(R.string.name), stringResource(R.string.job)),
            arrayOf(
                stringResource(R.string.phone),
                stringResource(R.string.location),
                stringResource(R.string.github),
                stringResource(R.string.email))
        )
    }
}