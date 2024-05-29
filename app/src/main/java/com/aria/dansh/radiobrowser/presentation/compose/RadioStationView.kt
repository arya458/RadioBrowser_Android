package com.aria.dansh.radiobrowser.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aria.dansh.radiobrowser.R
import com.aria.dansh.radiobrowserapi.models.RadioBrowserStation


@Composable
fun RadioStationView(station: RadioBrowserStation,onClick :() -> Unit) {


    Surface(modifier = Modifier
        .padding(10.dp)
        .wrapContentHeight()
        .width(100.dp)
        .clickable {
            onClick()
        }, color = MaterialTheme.colorScheme.surface, shadowElevation = 5.dp) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = station.favicon,
                placeholder = painterResource(id = R.drawable.icon),
                contentDescription = null,
            )
            Spacer(Modifier.size(10.dp))
            Text(
                station.name,
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(5.dp),
                style = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                textAlign = TextAlign.Center
            )
        }

    }



}