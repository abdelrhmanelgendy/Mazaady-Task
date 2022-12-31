package org.mazaady.com.presentation.bottom_sheet_dialog.adapter

import androidx.recyclerview.widget.DiffUtil
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadEntry

typealias mazaadEntries = List<MazaadEntry>


class MazaadDataViewerDiffUtils constructor(
    private val oldList: mazaadEntries,
    private val updatedList: mazaadEntries
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = updatedList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList.get(oldItemPosition)).equals (updatedList.get(newItemPosition))
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            (!oldList.get(oldItemPosition).equals(updatedList.get(newItemPosition))) -> false
              else -> true
        }
    }
}