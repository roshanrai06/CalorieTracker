package com.roshan.tracker_domain.use_case

data class TrackerUseCases(
    val trackFood: TrackFoodUseCase,
    val searchFood: SearchFoodUseCase,
    val getFoodsForDateUseCase: GetFoodsForDateUseCase,
    val deleteTrackedFood: DeleteTrackedFoodUseCase,
    val calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase
)