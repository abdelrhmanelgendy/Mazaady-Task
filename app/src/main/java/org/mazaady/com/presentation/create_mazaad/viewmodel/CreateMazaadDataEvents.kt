package org.mazaady.com.presentation.create_mazaad.viewmodel

sealed class CreateMazaadDataEvents {

    object GetCreateMazaadData : CreateMazaadDataEvents()
    data class GetSubCreateMazaadProps(val id:String) : CreateMazaadDataEvents()
    data class GetChildOptions(val id:String) : CreateMazaadDataEvents()


}
