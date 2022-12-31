package org.mazaady.com.data.network

import org.mazaady.com.data.network.dto.category_models.AllCategories
import org.mazaady.com.data.network.subcategory_props_model.SubCategoryProps
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface MazaadApi {

    @GET("api/get_all_cats")
    @Headers("Accept-language: en")
    suspend fun getAllCategoryData(): AllCategories


    @GET("api/properties")
    @Headers("Accept-language: en")
    suspend fun getSubCategoryProps(
        @Query("cat") subCatId: String
    ): SubCategoryProps
}