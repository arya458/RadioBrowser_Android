package com.aria.dansh.radiobrowser.domain.repository

import com.aria.dansh.radiobrowserapi.RadioBrowserApi


interface RadioRepository {


    fun radioBrowser(): RadioBrowserApi

//    fun getListTopVoteStations(limit: Int): Flow<RadioState<Stream<Station>>>
//
//    fun getListTopClickStations(limit: Int): Flow<RadioState<Stream<Station>>>
//
//    fun getSearchResult(search: AdvancedSearch, limit: Int): Flow<RadioState<Stream<Station>>>

}