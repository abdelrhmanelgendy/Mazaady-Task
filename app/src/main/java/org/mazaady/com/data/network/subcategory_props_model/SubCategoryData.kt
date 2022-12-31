package org.mazaady.com.data.network.subcategory_props_model

data class SubCategoryData(
    val description: Any?=null,
    val id: Int?=null,
    val list: Boolean?=null,
    val name: String?=null,
    val options: List<Option>?=null,
    val other_value: Any?=null,
    val parent: Any?=null,
    val slug: String?=null,
    val type: String?=null,
    val value: String?=null,
var selected:String?=null
)