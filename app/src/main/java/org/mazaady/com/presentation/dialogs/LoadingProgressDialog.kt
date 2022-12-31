package org.mazaady.com.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import org.mazaady.com.R
import org.mazaady.com.databinding.CustomeProgressDialogBinding


class LoadingProgressDialog(context: Context) {
    val dialog: Dialog
      var customeProgressDialogBinding: CustomeProgressDialogBinding

    init {
        dialog = Dialog(context)
        customeProgressDialogBinding=CustomeProgressDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setCancelable(true)
         dialog.window?.decorView?.setBackgroundResource(android.R.color.transparent)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(customeProgressDialogBinding.root)
    }

      fun show(progressText:String="Loading")
    {
       val txt:TextView= dialog.findViewById(R.id.progressDialog_txtLoading)
        txt.text = progressText
        dialog.show()
    }
      fun dismiss(){
        dialog.dismiss()
    }

}