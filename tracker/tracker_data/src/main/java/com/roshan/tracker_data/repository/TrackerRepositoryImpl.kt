package com.roshan.tracker_data.repository

import com.roshan.tracker_data.local_data.TrackerDAO
import com.roshan.tracker_data.mapper.toTrackableFood
import com.roshan.tracker_data.mapper.toTrackedFood
import com.roshan.tracker_data.mapper.toTrackedFoodEntity
import com.roshan.tracker_data.remote.OpenFoodApi
import com.roshan.tracker_domain.model.TrackableFood
import com.roshan.tracker_domain.model.TrackedFood
import com.roshan.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val trackerDao: TrackerDAO,
    private val openFoodApi: OpenFoodApi
) :
    TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = openFoodApi.searchFood(query, page, pageSize)
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates100g * 4f +
                                    it.nutriments.proteins100g * 4f +
                                    it.nutriments.fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull { it.toTrackableFood() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        trackerDao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        trackerDao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackerDao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities -> entities.map { it.toTrackedFood() } }
    }
}