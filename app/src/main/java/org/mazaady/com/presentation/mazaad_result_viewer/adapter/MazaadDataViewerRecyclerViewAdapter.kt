package org.mazaady.com.presentation.bottom_sheet_dialog.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.mazaady.com.R
import org.mazaady.com.databinding.MazaadRecyclerViewItemBinding
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadEntry
import org.mazaady.com.presentation.util.OnChoiceSelectedListener

class MazaadDataViewerRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<MazaadDataViewerRecyclerViewAdapter.MazaadDataViewerViewHolder>() {

    private var userData = emptyList<MazaadEntry>()



    fun setData(updatedList: List<MazaadEntry>) {
        val projectsDiffUtil = MazaadDataViewerDiffUtils(userData, updatedList)
        val calculateDiffResult = DiffUtil.calculateDiff(projectsDiffUtil)
        userData = updatedList
        calculateDiffResult.dispatchUpdatesTo(this)
    }

    lateinit var   binding: MazaadRecyclerViewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MazaadDataViewerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding=MazaadRecyclerViewItemBinding.inflate(inflater)
        return MazaadDataViewerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MazaadDataViewerViewHolder, position: Int) {
        holder.bind(context = context,position, userData,binding)

    }

    override fun getItemCount(): Int {
        return userData.size
    }

    class MazaadDataViewerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            context:Context,
            position: Int,
            userData: List<MazaadEntry>,
            binding: MazaadRecyclerViewItemBinding
        ) {
            if(position==0||position%2==0)
            {
                binding.mazaadItemLayout.setBackgroundColor(ContextCompat.getColor(context,R.color.tableColor))
            }
            val userDataEntry = userData.get(position)
            binding.mazaadItemKey.text=userDataEntry.name
            binding.mazaadItemValue.text=userDataEntry.value
        }



    }

}