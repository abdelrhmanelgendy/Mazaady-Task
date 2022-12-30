package org.mazaady.com.data.network

import org.mazaady.com.data.network.dto.models.AllCategories
import retrofit2.http.GET


interface MazaadApi {

    @GET("api/get_all_cats")
    suspend fun getAllCategoryData(): AllCategories


}