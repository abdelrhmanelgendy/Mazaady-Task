package org.mazaady.com.domain.category.repository

import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.data.child_options.dto.ChildOptions

interface CategoryRepository {
    suspend  fun getAllCategories(): AllCategories
  }