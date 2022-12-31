package org.mazaady.presentation.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import org.mazaady.com.R
import org.mazaady.com.presentation.dialogs.LoadingProgressDialog


fun View.hideKeyboard(context: Context) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Context.showToast(@StringRes messageRes: Int, length: Int = Toast.LENGTH_LONG) {
   Toast.makeText(this, resources.getString(messageRes), length).show()
 }
fun Context.showProgressDialog(progressMsg:String="Loading"):LoadingProgressDialog{
    val loadingProgressDialog =LoadingProgressDialog(this)
    loadingProgressDialog.show(progressMsg)
    return  loadingProgressDialog
}
fun LoadingProgressDialog.dismissProgressDialog(){
  this.dismiss()
}


fun TextInputEditText.enable()
{
    this.setFocusable(true)
    this.setFocusableInTouchMode(true)
    this.setClickable(true)
}
fun TextInputEditText.disable()
{
    this.setFocusable(false)
    this.setFocusableInTouchMode(false)
    this.setClickable(false)
}

