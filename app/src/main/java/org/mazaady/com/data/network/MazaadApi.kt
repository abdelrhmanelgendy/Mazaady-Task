package org.mazaady.com.data.network

import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps
import org.mazaady.com.data.network.entity.child_options_model.ChildOptions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
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

    @GET("api/get-options-child/{child_id}")
    @Headers("Accept-language: en")
    suspend fun getChildOptions(
        @Path("child_id") childId: String
    ): ChildOptions
}