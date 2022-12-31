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
import org.mazaady.com.databinding.MazaadGoodsItemsBinding
import org.mazaady.com.presentation.util.OnChoiceSelectedListener

class MazaadSimilarGoodsAdapter(
    private val context: Context,
) :
    RecyclerView.Adapter<MazaadSimilarGoodsAdapter.MazaadSimilarGoodsViewHolder>() {



    lateinit var binding:MazaadGoodsItemsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MazaadSimilarGoodsViewHolder {
        val inflater = LayoutInflater.from(context)
        binding=MazaadGoodsItemsBinding.inflate(inflater)
        return MazaadSimilarGoodsViewHolder(binding.root)
    }


    override fun getItemCount(): Int {
        return 10
    }
    override fun onBindViewHolder(
        holder: MazaadSimilarGoodsAdapter.MazaadSimilarGoodsViewHolder,
        position: Int
    ) {

    }
    class MazaadSimilarGoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }



}