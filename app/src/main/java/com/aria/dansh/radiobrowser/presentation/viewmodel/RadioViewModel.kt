package com.aria.dansh.radiobrowser.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aria.dansh.radiobrowserapi.util.RadioState
import com.aria.dansh.radiobrowser.domain.repository.RadioRepository
import com.aria.dansh.radiobrowserapi.models.RadioBrowserStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.Vector
import java.util.stream.Stream
import javax.inject.Inject


//Todo:: ViewModel Here

@HiltViewModel
class RadioViewModel @Inject constructor(
    private val radioRepository: RadioRepository,
) : ViewModel() {

//    private val searchBuilder = AdvancedSearch.builder()
    private val limit = 100

    private val _radioState: MutableStateFlow<RadioState<List<RadioBrowserStation>>> = MutableStateFlow(
        RadioState.Loading())
    val radioState: StateFlow<RadioState<List<RadioBrowserStation>>> = _radioState


    init {
        getRadioTopVoteStations()
    }
    fun getRadioTopVoteStations() {
        viewModelScope.launch {

            radioRepository.radioBrowser().getStationsByVote(0,limit).collectLatest {

                _radioState.value = it

            }

        }
    }



}