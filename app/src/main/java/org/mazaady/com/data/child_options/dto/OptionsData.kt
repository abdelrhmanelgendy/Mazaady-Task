package org.mazaady.com.data.child_options.dto

data class OptionsData(
    val description: Any,
    val id: Int,
    val list: Boolean,
    val name: String,
    val options: List<Option>,
    val other_value: Any,
    val parent: Int,
    val slug: String,
    val type: Any,
    val value: String
)