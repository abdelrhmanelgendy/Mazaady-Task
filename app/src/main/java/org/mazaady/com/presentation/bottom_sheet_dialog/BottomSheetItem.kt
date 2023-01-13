package org.mazaady.com.presentation.bottom_sheet_dialog


data class BottomSheetTiles(
    var name:String,
    val items:ArrayList<BottomSheetItem>,
    var selectedOption:String="",
    var isOtherAdded :Boolean?=false,
    var parent :String?=""


)
data class BottomSheetItem(
    val name:String,
    val id:Int,
    val parent:Int,
    val  child:Boolean,
    var isOtherAdded :Boolean?
){
    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        other as BottomSheetItem
        if (name != other.name) return false
        return true
    }


}
