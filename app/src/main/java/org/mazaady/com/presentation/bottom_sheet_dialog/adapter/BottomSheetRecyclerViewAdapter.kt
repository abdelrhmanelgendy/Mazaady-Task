package org.mazaady.com.presentation.bottom_sheet_dialog.adapter

import NamesDiffUtils
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.mazaady.com.R
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem
import org.mazaady.com.presentation.util.OnChoiceSelectedListener

class BottomSheetRecyclerViewAdapter(
    private val context: Context,
    private  val onItemSelection: OnChoiceSelectedListener
) :
    RecyclerView.Adapter<BottomSheetRecyclerViewAdapter.BottomSheetViewHolder>() {

    private var userChoices = emptyList<BottomSheetItem>()



    fun setData(updatedList: List<BottomSheetItem>) {

        val projectsDiffUtil = NamesDiffUtils(userChoices, updatedList)
        val calculateDiffResult = DiffUtil.calculateDiff(projectsDiffUtil)
        userChoices = updatedList
        calculateDiffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_item, null)
        return BottomSheetViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        holder.bind(position, userChoices,onItemSelection)

    }

    override fun getItemCount(): Int {
        return userChoices.size
    }

    class BottomSheetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var etUserChoice: TextView
        fun bind(
            position: Int,
            userChoices: List<BottomSheetItem>,
            onItemSelection: OnChoiceSelectedListener
        ) {
            etUserChoice = itemView.findViewById(R.id.txtChoice)
            val currentItem = userChoices[position]
             etUserChoice.text = currentItem.toString()
            itemView.setOnClickListener {
                onItemSelection.onSelect(currentItem)
            }
        }

    }

}