package org.mazaady.com.presentation

sealed class CreateMazaadDataEvents {

    object GetCreateMazaadData : CreateMazaadDataEvents()
    data class GetSubCreateMazaadProps(val id:String) : CreateMazaadDataEvents()


}