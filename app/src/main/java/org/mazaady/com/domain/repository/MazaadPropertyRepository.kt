package org.mazaady.com.domain.repository

import org.mazaady.com.data.network.dto.category_models.AllCategories
import org.mazaady.com.data.network.subcategory_props_model.SubCategoryProps

interface MazaadPropertyRepository {

    suspend  fun getAllCategories(): AllCategories
    suspend  fun getSubCategoryProps(id:String): SubCategoryProps
 }