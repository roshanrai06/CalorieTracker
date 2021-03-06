package com.roshan.tracker_data.local_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roshan.tracker_data.local_data.entity.TrackedFoodEntity


@Database(entities = [TrackedFoodEntity::class], version = 1)
abstract class TrackerDatabase : RoomDatabase() {
    abstract val trackerDao: TrackerDAO
}