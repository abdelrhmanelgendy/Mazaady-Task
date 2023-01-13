package org.mazaady.com.domain.child_options.repository

import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.data.child_options.dto.ChildOptions

interface ChildOptionsRepository {
    suspend  fun getChildOptions(id:String): ChildOptions
 }