package org.mazaady.com.presentation.mazaad_result_viewer.adapter


import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.presentation.bottom_sheet_dialog.adapter.MazaadDataViewerDiffUtils
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadEntry

@RunWith(JUnit4::class)
class MazaadDataViewerDiffUtilsTest {

    @Test
    fun `test getting the old and new list sizes with empty lists returns zero`() {
        val namesDiffUtils =
            MazaadDataViewerDiffUtils(oldList = buildEmptyOldList(), buildEmptyNewList())
        Assert.assertEquals(namesDiffUtils.newListSize, 0)
        Assert.assertEquals(namesDiffUtils.oldListSize, 0)
    }

    @Test
    fun `test getting the old and new list sizes with non empty lists returns correct size`() {
        val namesDiffUtils = MazaadDataViewerDiffUtils(oldList = buildOldList(), buildNewList())
        Assert.assertEquals(namesDiffUtils.newListSize, buildNewList().size)
        Assert.assertEquals(namesDiffUtils.oldListSize, buildOldList().size)
    }

    @Test
    fun `test getting the value of the content the same with different first item returns false`() {
        val namesDiffUtils = MazaadDataViewerDiffUtils(oldList = buildOldList(), buildNewList())
        Assert.assertEquals(namesDiffUtils.areContentsTheSame(0, 0), false)
    }

    @Test
    fun `test getting the value of the item the same with different item returns false`() {
        val namesDiffUtils = MazaadDataViewerDiffUtils(oldList = buildOldList(), buildNewList())
        Assert.assertEquals(namesDiffUtils.areItemsTheSame(0, 0), false)
    }

    @Test
    fun `test getting the value of the content the same with the different second item returns false`() {
        val namesDiffUtils = MazaadDataViewerDiffUtils(oldList = buildOldList(), buildNewList())
        Assert.assertEquals(namesDiffUtils.areContentsTheSame(1, 1), false)
    }


    private fun buildEmptyOldList(): List<MazaadEntry> {
        return emptyList()
    }

    private fun buildOldList(): List<MazaadEntry> {
        return listOf(MazaadEntry("Category", "Cars"),MazaadEntry("Process Type", "Sell"))
    }

    private fun buildEmptyNewList(): List<MazaadEntry> {
        return emptyList()
    }

    private fun buildNewList(): List<MazaadEntry> {
        return listOf(MazaadEntry("Category2", "Cars2"),MazaadEntry("Process Type2", "Sell2"))

    }

}