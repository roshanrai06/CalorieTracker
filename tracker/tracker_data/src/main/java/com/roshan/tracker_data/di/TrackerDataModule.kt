package com.roshan.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.roshan.tracker_data.local_data.TrackerDAO
import com.roshan.tracker_data.local_data.TrackerDatabase
import com.roshan.tracker_data.remote.OpenFoodApi
import com.roshan.tracker_data.remote.OpenFoodApi.Companion.BASE_URL
import com.roshan.tracker_data.repository.TrackerRepositoryImpl
import com.roshan.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    @Provides
    @Singleton
    fun providesOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client).build().create()
    }

    @Provides
    @Singleton
    fun providesTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(app, TrackerDatabase::class.java, "tracker_db").build()
    }

    @Provides
    @Singleton
    fun providesTrackerRepository(
        openFoodApi: OpenFoodApi,
        trackerDb: TrackerDatabase
    ): TrackerRepository {
        return TrackerRepositoryImpl(trackerDb.trackerDao, openFoodApi)
    }
}