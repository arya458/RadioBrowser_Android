package com.aria.dansh.radiobrowser.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aria.dansh.radiobrowser.presentation.compose.RadioStationView
import com.aria.dansh.radiobrowser.presentation.viewmodel.RadioViewModel
import com.aria.dansh.radiobrowserapi.util.RadioState


@Composable
fun MainPage(radioViewModel: RadioViewModel = hiltViewModel()){

    val radioState by radioViewModel.radioState.collectAsState()
    Surface(
        Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {


        when(radioState){
            is RadioState.Error -> {

                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "Error")
                    Text(text = radioState.errorMsg.toString())
                }

            }
            is RadioState.Loading -> {

                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(text = "Loading ...")

                }

            }
            is RadioState.Success -> {
                (radioState as RadioState.Success).data?.let {
                LazyColumn(Modifier.fillMaxSize()) {
                        items(it){

                            RadioStationView(station = it) {
                                
                            }

                        }
                    }

                }

            }
        }


    }


}