package org.mazaady.com.data.subCategory.api

import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.data.child_options.dto.ChildOptions
import org.mazaady.com.utils.End_points
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface SubCategoryApi {
    @GET(End_points.GTE_SUB_CATEGORY)
    @Headers("Accept-language: en")
    suspend fun getSubCategoryProps(
        @Query("cat") subCatId: String
    ): SubCategoryProps

}