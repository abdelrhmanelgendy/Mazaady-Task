package org.mazaady.com.presentation.bottom_sheet_dialog

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.mazaady.com.databinding.BottomSheetSelectionBinding
import org.mazaady.com.presentation.bottom_sheet_dialog.adapter.BottomSheetRecyclerViewAdapter
import org.mazaady.com.presentation.util.OnChoiceSelectedListener


class BottomSheetSelectionDialog(
    context: Context,
    private val choices: List<String>,
    onItemSelection: OnChoiceSelectedListener
) {
    var bottomSheetDialog: BottomSheetDialog
    var sheetSelectionBinding: BottomSheetSelectionBinding
    var bottomSheetRecyclerViewAdapter: BottomSheetRecyclerViewAdapter

    init {
        bottomSheetDialog = BottomSheetDialog(context)
        sheetSelectionBinding = BottomSheetSelectionBinding.inflate(LayoutInflater.from(context))

        bottomSheetDialog.setContentView(sheetSelectionBinding.root)
        val dialog = bottomSheetDialog
        bottomSheetRecyclerViewAdapter = BottomSheetRecyclerViewAdapter(context,onItemSelection)
        with(sheetSelectionBinding.choicesList) {
            layoutManager = LinearLayoutManager(context)
            adapter = bottomSheetRecyclerViewAdapter
        }


        modifyKeyboardView(dialog)

        listenToListFilter()
    }

    private fun modifyKeyboardView(dialog: BottomSheetDialog) {
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog.setOnShowListener {
            Handler().post {
                val bottomSheet =
                    (dialog as? BottomSheetDialog)?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as? FrameLayout
                bottomSheet?.let {
                    BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }

    private fun listenToListFilter() {
        sheetSelectionBinding.etSeacrh.addTextChangedListener {
            Log.d("TAG500", "listenToListFilter: "+it.toString())
            val txt = it.toString()
            if (txt.toString().isEmpty()) {
                bottomSheetRecyclerViewAdapter.setData(choices)

            } else {
                val filteredList = choices.filter { choice ->
                    choice.toLowerCase().contains(txt.toLowerCase())
                }
                Log.d("TAG500", "listenToListFilter: "+filteredList)
                bottomSheetRecyclerViewAdapter.setData(filteredList)
            }
        }
    }

    fun show(title: String) {
        sheetSelectionBinding.txtTitle.setText(title)
        bottomSheetRecyclerViewAdapter.setData(choices)
        bottomSheetDialog.show()
    }
    fun dismiss(){
        bottomSheetDialog.dismiss()
    }
}