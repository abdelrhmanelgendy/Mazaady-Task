package org.mazaady.com.domain.usecase

import org.mazaady.com.presentation.create_mazaad.CreateMazaadActivity.Companion.OTHER
import org.mazaady.com.presentation.create_mazaad.model.LinearEditeTextItem
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadEntry

class AddUserInputsWithoutOtherUseCase {
    operator fun invoke(
        dropDownETList: List<LinearEditeTextItem>,
        userInputs: ArrayList<MazaadEntry>
    ){
        dropDownETList.forEachIndexed { index, linearEditeTextItem ->
            if (!linearEditeTextItem.name.equals(OTHER)) {
                val name = linearEditeTextItem.name
                var value = linearEditeTextItem.item.editText!!.text.toString()
                if (linearEditeTextItem.item.editText!!.text.toString().equals(OTHER)) {
                    value = dropDownETList.get(index + 1).item.editText!!.text.toString()
                }
                userInputs.add(MazaadEntry(name, value))
            }


        }
    }
}