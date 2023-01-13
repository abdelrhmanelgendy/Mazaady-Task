package org.mazaady.com.domain.usecase

import android.content.Context
import org.mazaady.com.R
import org.mazaady.com.presentation.create_mazaad.model.LinearEditeTextItem

class ValidateUserInputsUseCase {
    operator fun invoke(dropDownETList:List<LinearEditeTextItem>, context: Context):Boolean{
        for (mutableEntry in dropDownETList) {
                if (mutableEntry.item.editText?.text.toString().isEmpty()) {
                    mutableEntry.item.error =
                        context.getString(R.string.please_specify) + " ${mutableEntry.name}"
                    return false
                } else {
                    mutableEntry.item.error = null
                }
            }
            return true


    }
}