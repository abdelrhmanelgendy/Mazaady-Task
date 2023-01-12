package org.mazaady.com.data.network.entity.child_options_model

data class Option(
    val child: Boolean,
    val id: Int,
    val name: String,
    val parent: Int,
    val slug: String
)