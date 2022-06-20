package com.roshan.tracker_domain.di

import com.roshan.core.domain.preferences.Preferences
import com.roshan.tracker_domain.repository.TrackerRepository
import com.roshan.tracker_domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {
    @Provides
    @ViewModelScoped
    fun provideTrackerUserCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            TrackFoodUseCase(repository),
            SearchFoodUseCase(repository),
            GetFoodsForDateUseCase(repository),
            DeleteTrackedFoodUseCase(repository),
            CalculateMealNutrientsUseCase(preferences)
        )
    }
}