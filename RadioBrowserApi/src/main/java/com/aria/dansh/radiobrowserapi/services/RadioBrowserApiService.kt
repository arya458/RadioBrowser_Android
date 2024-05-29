package com.aria.dansh.radiobrowserapi.services

import com.aria.dansh.radiobrowserapi.models.RadioBrowserClickResult
import com.aria.dansh.radiobrowserapi.models.RadioBrowserCountry
import com.aria.dansh.radiobrowserapi.models.RadioBrowserOrder
import com.aria.dansh.radiobrowserapi.models.RadioBrowserState
import com.aria.dansh.radiobrowserapi.models.RadioBrowserStation
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RadioBrowserApiService {
    @GET("json/countries")
    suspend fun getCountries(
        @Header("User-Agent") userAgent: String,
        @Query("order") order: String = RadioBrowserOrder.BY_NAME.getApiValue()
    ): List<RadioBrowserCountry>

    @GET("json/states/{countryName}/")
    suspend fun getStatesByCountryName(
        @Header("User-Agent") userAgent: String,
        @Path("countryName") countryName: String
    ): List<RadioBrowserState>

    @GET("json/stations/bycountrycodeexact/{countryCode}")
    suspend fun getStationsByCountry(
        @Header("User-Agent") userAgent: String,
        @Path("countryCode") countryCode: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100
    ): List<RadioBrowserStation>

    @GET("json/stations/byname/{search}")
    suspend fun getStationsBySearch(
        @Header("User-Agent") userAgent: String,
        @Path("search") search: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100
    ): List<RadioBrowserStation>

    @GET("json/stations/bystateexact/{stateName}")
    suspend fun getStationsByState(
        @Header("User-Agent") userAgent: String,
        @Path("stateName") stateName: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100
    ): List<RadioBrowserStation>

    @GET("json/stations")
    suspend fun getStationsByVote(
        @Query("order") order: String = "votes",
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100
    ): List<RadioBrowserStation>

    @GET("json/url/{stationUuid}")
    suspend fun stationClick(
        @Header("User-Agent") userAgent: String,
        @Path("stationUuid") stationUuid: String
    ): RadioBrowserClickResult
}