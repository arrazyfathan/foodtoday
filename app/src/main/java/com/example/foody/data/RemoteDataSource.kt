package com.example.foody.data

import android.util.Log
import com.example.foody.data.network.FoodRecipesApi
import com.example.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipe(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipe(searchQuery)
    }
}