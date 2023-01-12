package org.mazaady.com.domain.usecase

import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps

class GetListOfOptionsBySubCategoryProps {
    operator fun invoke(subCategoryProps: SubCategoryProps, optionName:String):
           List<String>  {
      return  subCategoryProps.data?.firstOrNull {
            it.name.equals(optionName)
        }?.options?.map {
            it.name?:""
        }?: arrayListOf()
    }
}