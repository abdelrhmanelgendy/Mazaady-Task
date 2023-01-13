package org.mazaady.com.data.category.dto

data class Data(
    val ads_banners: List<AdsBanner>?=null,
    val categories: List<Category>?=null,
    val google_version: String?=null,
    val huawei_version: String?=null,
    val ios_version: String?=null,
    val statistics: Statistics?=null
)