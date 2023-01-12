package org.mazaady.com.domain.repository

import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps
import org.mazaady.com.data.network.entity.child_options_model.ChildOptions

interface MazaadPropertyRepository {

    suspend  fun getAllCategories(): AllCategories
    suspend  fun getSubCategoryProps(id:String): SubCategoryProps
    suspend  fun getChildOptions(id:String): ChildOptions
 }