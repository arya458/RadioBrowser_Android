package com.aria.dansh.radiobrowser.di


import android.app.Application

import com.aria.dansh.radiobrowser.data.repository.RadioRepositoryImpl
import com.aria.dansh.radiobrowser.domain.repository.RadioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//Todo:: Hilt Provides
@Module
@InstallIn(SingletonComponent::class)
class RadioModule {

    @Provides
    @Singleton
    fun providesRepository(
        context : Application
    ): RadioRepository = RadioRepositoryImpl(context)

//    @Provides
//    @Singleton
//    fun providesBroadcastReceiver(): MyBroadcastReceiver = MyBroadcastReceiver()
//
//
//    @Provides
//    @Singleton
//    fun providesNotificationWorker() = OneTimeWorkRequestBuilder<NotificationWorker>()




}