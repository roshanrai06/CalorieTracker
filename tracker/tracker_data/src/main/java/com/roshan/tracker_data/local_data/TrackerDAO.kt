package com.roshan.tracker_data.local_data

import androidx.room.*
import com.roshan.tracker_data.local_data.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query("SELECT*FROM trackedfoodentity WHERE dayOfMonth=:day AND month=:month AND year=:year")
     fun getFoodsForDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}