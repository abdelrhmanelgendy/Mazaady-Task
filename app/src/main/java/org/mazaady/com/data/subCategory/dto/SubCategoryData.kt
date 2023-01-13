package org.mazaady.com.data.subCategory.dto

data class SubCategoryData(
    val description: Any?=null,
    val id: Int?=null,
    val list: Boolean?=null,
    val name: String?=null,
    val options: List<Option>?=null,
    val other_value: Any?=null,
    val parent: Int?=null,
    val slug: String?=null,
    val type: String?=null,
    val value: String?=null,
    var selected:String?=null
){
    override fun toString(): String {
        return "$name"
    }
}