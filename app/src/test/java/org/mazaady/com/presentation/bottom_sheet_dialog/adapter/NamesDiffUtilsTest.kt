package org.mazaady.com.presentation.bottom_sheet_dialog.adapter

import NamesDiffUtils
import itemsData
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem

@RunWith(JUnit4::class)
class NamesDiffUtilsTest{

    @Test
    fun `test getting the old and new list sizes with empty lists returns zero`() {
        val namesDiffUtils = NamesDiffUtils(oldList = buildEmptyOldList(),buildEmptyNewList())
        Assert.assertEquals(namesDiffUtils.newListSize, 0)
        Assert.assertEquals(namesDiffUtils.oldListSize, 0)
    }
    @Test
    fun `test getting the old and new list sizes with non empty lists returns correct size`() {
        val namesDiffUtils = NamesDiffUtils(oldList = buildOldList(),buildNewList())
        Assert.assertEquals(namesDiffUtils.newListSize, buildNewList().size)
        Assert.assertEquals(namesDiffUtils.oldListSize, buildOldList().size)
    }
    @Test
    fun `test getting the value of the content the same with different first item returns false`() {
        val namesDiffUtils = NamesDiffUtils(oldList = buildOldList(),buildNewList())
        Assert.assertEquals(namesDiffUtils.areContentsTheSame(0, 0), false)
    }

    @Test
    fun `test getting the value of the item the same with different item returns false`() {
        val namesDiffUtils = NamesDiffUtils(oldList = buildOldList(),buildNewList())
        Assert.assertEquals(namesDiffUtils.areItemsTheSame(0, 0), false)
    }

    @Test
    fun `test getting the value of the content the same with the different second item returns false`() {
        val namesDiffUtils = NamesDiffUtils(oldList = buildOldList(),buildNewList())
        Assert.assertEquals(namesDiffUtils.areContentsTheSame(1, 1), false)
    }



    private fun buildEmptyOldList(): itemsData {
        return emptyList()
    }
    private fun buildOldList(): itemsData {
        val item1= "CARS , MOTORCYCLES & ACCESSORIES"
        val item2= "REAL ESTATE , Trade Names"
        return listOf(BottomSheetItem(
            item1,-1,-1,false,false
        ),BottomSheetItem(
            item2,-1,-1,false,false
        )
        )
    }

    private fun buildEmptyNewList(): itemsData {
        return emptyList()
    }
    private fun buildNewList(): itemsData {
        val item1= "ANIMALS & ACCESSORIES"
        val item2= "EQUIPMENTS , TOOLS"
        return listOf(BottomSheetItem(
            item1,-1,-1,false,false
        ),BottomSheetItem(
            item2,-1,-1,false,false
        )
        )    }

}