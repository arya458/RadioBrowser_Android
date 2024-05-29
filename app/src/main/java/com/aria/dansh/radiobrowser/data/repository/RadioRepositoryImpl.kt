package com.aria.dansh.radiobrowser.data.repository

import android.app.Application
import android.util.Log
import com.aria.dansh.radiobrowserapi.util.RadioState
import com.aria.dansh.radiobrowser.domain.repository.RadioRepository
import com.aria.dansh.radiobrowserapi.RadioBrowserApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.InetAddress
import java.util.stream.Stream
import javax.inject.Singleton

@Singleton
class RadioRepositoryImpl(
    private val context: Application,
) : RadioRepository {



    override fun radioBrowser(): RadioBrowserApi {
        return RadioBrowserApi()
    }

}