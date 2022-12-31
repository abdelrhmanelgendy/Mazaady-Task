package org.mazaady.com.presentation.mazaad_result_viewer.model

data class MazaadEntry (
    val name:String,
    val value:String
):java.io.Serializable
data class MazaadDataModel (
    val data:List<MazaadEntry>
):java.io.Serializable