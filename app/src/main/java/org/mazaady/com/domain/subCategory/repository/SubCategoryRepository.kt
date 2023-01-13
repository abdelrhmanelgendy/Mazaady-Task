package org.mazaady.com.domain.subCategory.repository

import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.data.child_options.dto.ChildOptions

interface SubCategoryRepository {

     suspend  fun getSubCategoryProps(id:String): SubCategoryProps
  }