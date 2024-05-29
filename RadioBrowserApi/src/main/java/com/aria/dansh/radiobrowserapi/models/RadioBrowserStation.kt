package com.aria.dansh.radiobrowserapi.models

data class RadioBrowserStation(
    val stationuuid: String,
    val name: String,
    val url: String,
    val url_resolved: String,
    val homepage: String,
    val favicon: String
)
