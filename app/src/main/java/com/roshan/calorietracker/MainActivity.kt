package com.roshan.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roshan.calorietracker.navigation.navigate
import com.roshan.calorietracker.ui.theme.CalorieTrackerTheme
import com.roshan.core.navigation.Route
import com.roshan.onboarding_presentation.activity.ActivityScreen
import com.roshan.onboarding_presentation.age.AgeScreen
import com.roshan.onboarding_presentation.gender.Gender
import com.roshan.onboarding_presentation.goal.GoalScreen
import com.roshan.onboarding_presentation.height.HeightScreen
import com.roshan.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.roshan.onboarding_presentation.weight.WeightScreen
import com.roshan.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
                    NavHost(navController = navController, startDestination = Route.WELCOME) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(scaffoldState, onNavigate = navController::navigate)
                        }
                        composable(Route.GENDER) {
                            Gender(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(scaffoldState, onNavigate = navController::navigate)
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(scaffoldState, onNavigate = navController::navigate)
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(scaffoldState, onNavigate = navController::navigate)
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.TRACKER_OVERVIEW) {

                        }
                        composable(Route.SEARCH) {

                        }
                    }
                }

            }
        }
    }
}

