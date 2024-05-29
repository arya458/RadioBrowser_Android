package com.aria.dansh.radiobrowserapi

import android.util.Log
import com.aria.dansh.radiobrowserapi.models.RadioBrowserClickResult
import com.aria.dansh.radiobrowserapi.models.RadioBrowserCountry
import com.aria.dansh.radiobrowserapi.models.RadioBrowserOrder
import com.aria.dansh.radiobrowserapi.models.RadioBrowserState
import com.aria.dansh.radiobrowserapi.models.RadioBrowserStation
import com.aria.dansh.radiobrowserapi.services.RadioBrowserApiService
import com.aria.dansh.radiobrowserapi.util.RadioState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetAddress

private const val allServersEndpoint = "all.api.radio-browser.info"
private const val logTag = "RBA"
private const val initFailMsg = "RadioBrowser initialize failed to find available server"

class RadioBrowserApi(private val userAgent: String = "RC.RadioBrowserAndroid") {
    var radioBrowserService: RadioBrowserApiService? = null

    private suspend fun initialize() {
        if (radioBrowserService == null) {
            val serverEndpoint = getRadioBrowserServer()
            radioBrowserService = Retrofit.Builder()
                .baseUrl("https://$serverEndpoint")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RadioBrowserApiService::class.java)
        }
    }

    private suspend fun getRadioBrowserServer(): String = withContext(Dispatchers.IO) {
        val servers = InetAddress.getAllByName(allServersEndpoint)
        if (servers.isEmpty()) { return@withContext "" }
        servers.shuffle()
        return@withContext servers.first().canonicalHostName
    }

    private fun handleApiException(e: Exception, onFail: (String?) -> Unit) {
        e.message?.let { Log.e(logTag, it) }
        onFail.invoke(e.message)
    }



    fun getCountries(
        order: RadioBrowserOrder = RadioBrowserOrder.BY_NAME,
        onSuccess: (List<RadioBrowserCountry>) -> Unit,
        onFail: (String?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            initialize()
            radioBrowserService?.getCountries(
                userAgent = userAgent,
                order = order.getApiValue()
            )?.let(onSuccess) ?: onFail.invoke(initFailMsg)
        } catch (e: Exception) {
            handleApiException(e, onFail)
        }
    }

    suspend fun getCountries(
        order: RadioBrowserOrder = RadioBrowserOrder.BY_NAME
    ): Flow<RadioState<List<RadioBrowserCountry>>> {
        return flow<RadioState<List<RadioBrowserCountry>>> {
            emit(RadioState.Loading())

            try {
                initialize()
                radioBrowserService?.getCountries(
                    userAgent = userAgent,
                    order = order.getApiValue()
                )?.let {
                    emit(RadioState.Success(it))
                }
            } catch (e: Exception) {
                emit(RadioState.Error(e.message.toString()))
            }

        }
    }




    fun getStationsByCountry(
        countryCode: String,
        offset: Int = 0,
        limit: Int = 1000,
        onSuccess: (List<RadioBrowserStation>) -> Unit,
        onFail: (String?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            initialize()
            radioBrowserService?.getStationsByCountry(
                userAgent = userAgent,
                countryCode = countryCode,
                offset = offset,
                limit = limit
            )?.let(onSuccess) ?: onFail.invoke(initFailMsg)
        } catch (e: Exception) {
            handleApiException(e, onFail)
        }
    }

    suspend fun getStationsByCountry(
        countryCode: String,
        offset: Int = 0,
        limit: Int = 1000,
    ): Flow<RadioState<List<RadioBrowserStation>>> {
        return flow<RadioState<List<RadioBrowserStation>>> {
            emit(RadioState.Loading())

            try {
                initialize()
                radioBrowserService?.getStationsByCountry(
                    userAgent = userAgent,
                    countryCode = countryCode,
                    offset = offset,
                    limit = limit
                )?.let {
                    emit(RadioState.Success(it))
                }
            } catch (e: Exception) {
                emit(RadioState.Error(e.message.toString()))
            }

        }
    }




    fun getStationsByVote(
        offset: Int = 0,
        limit: Int = 1000,
        onSuccess: (List<RadioBrowserStation>) -> Unit,
        onFail: (String?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            initialize()
            radioBrowserService?.getStationsByVote(
                offset = offset,
                limit = limit
            )?.let(onSuccess) ?: onFail.invoke(initFailMsg)
        } catch (e: Exception) {
            handleApiException(e, onFail)
        }
    }

    suspend fun getStationsByVote(
        offset: Int = 0,
        limit: Int = 1000
    ): Flow<RadioState<List<RadioBrowserStation>>> {
        return flow<RadioState<List<RadioBrowserStation>>> {
            emit(RadioState.Loading())

            try {
                initialize()
                val res = radioBrowserService?.getStationsByVote(
                    offset = offset,
                    limit = limit
                )?.let {
                    emit(RadioState.Success(it))
                }
            } catch (e: Exception) {
                emit(RadioState.Error(e.message.toString()))
            }

        }
    }




    fun searchStationsByName(
        search: String,
        offset: Int = 0,
        limit: Int = 1000,
        onSuccess: (List<RadioBrowserStation>) -> Unit,
        onFail: (String?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        if (search.isEmpty()) {
            onFail.invoke("search cannot be empty")
            return@launch
        }
        try {
            initialize()
            radioBrowserService?.getStationsBySearch(
                userAgent = userAgent,
                search = search,
                offset = offset,
                limit = limit
            )?.let(onSuccess) ?: onFail.invoke(initFailMsg)
        } catch (e: Exception) {
            handleApiException(e, onFail)
        }
    }

    suspend fun searchStationsByName(
        search: String,
        offset: Int = 0,
        limit: Int = 1000,
    ): Flow<RadioState<List<RadioBrowserStation>>> {
        return flow<RadioState<List<RadioBrowserStation>>> {
            emit(RadioState.Loading())

            try {
                initialize()
                radioBrowserService?.getStationsBySearch(
                    userAgent = userAgent,
                    search = search,
                    offset = offset,
                    limit = limit
                )?.let {
                    emit(RadioState.Success(it))
                }
            } catch (e: Exception) {
                emit(RadioState.Error(e.message.toString()))
            }

        }
    }




    fun getStatesByCountry(
        countryName: String,
        onSuccess: (List<RadioBrowserState>) -> Unit,
        onFail: (String?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        if (countryName.isEmpty()) {
            onFail.invoke("countryName cannot be empty")
            return@launch
        }
        try {
            initialize()
            radioBrowserService?.getStatesByCountryName(
                userAgent = userAgent,
                countryName = countryName
            )?.let(onSuccess) ?: onFail.invoke(initFailMsg)
        } catch (e: Exception) {
            handleApiException(e, onFail)
        }
    }

    fun getStationsByState(
        stateName: String,
        offset: Int = 0,
        limit: Int = 1000,
        onSuccess: (List<RadioBrowserStation>) -> Unit,
        onFail: (String?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        if (stateName.isEmpty()) {
            onFail.invoke("stateName cannot be empty")
            return@launch
        }
        try {
            initialize()
            radioBrowserService?.getStationsByState(
                userAgent = userAgent,
                stateName = stateName,
                offset = offset,
                limit = limit
            )?.let(onSuccess) ?: onFail.invoke(initFailMsg)
        } catch (e: Exception) {
            handleApiException(e, onFail)
        }
    }

    fun stationClick(
        stationUuid: String,
        onSuccess: (RadioBrowserClickResult) -> Unit,
        onFail: (String?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        if (stationUuid.isEmpty()) {
            onFail.invoke("stationUuid cannot be empty")
            return@launch
        }
        try {
            initialize()
            radioBrowserService?.stationClick(
                userAgent = userAgent,
                stationUuid = stationUuid
            )?.let(onSuccess) ?: onFail.invoke(initFailMsg)
        } catch (e: Exception) {
            handleApiException(e, onFail)
        }
    }
}