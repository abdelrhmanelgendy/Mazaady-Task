package org.mazaady.com.presentation.create_mazaad

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.mazaady.com.R
import org.mazaady.com.data.network.dto.category_models.AllCategories
import org.mazaady.com.data.network.subcategory_props_model.SubCategoryProps
import org.mazaady.com.databinding.ActivityMainBinding
import org.mazaady.com.domain.usecase.GetIdOfSubCategoryByName
import org.mazaady.com.domain.usecase.GetListOfStringFromAllCategoriesUseCase
import org.mazaady.com.domain.usecase.GetListOfSubCategoryByCategoryName
import org.mazaady.com.presentation.CreateMazaadDataEvents
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetSelectionDialog
import org.mazaady.com.presentation.dialogs.LoadingProgressDialog
import org.mazaady.com.presentation.mazaad_result_viewer.MazaadResultActivity
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadDataModel
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadEntry
import org.mazaady.com.presentation.util.OnChoiceSelectedListener
import org.mazaady.presentation.util.*


@AndroidEntryPoint
class CreateMazaadActivity : AppCompatActivity() {
    private val TAG = "MainActivityTAG"
    private val viewModel: CreateMazaadViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val OTHER = "Other"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObserver()
        viewModel.onEvent(CreateMazaadDataEvents.GetCreateMazaadData);

        listenToSubmitButton()

    }

    private fun listenToSubmitButton() {
        binding.btnSubmit.setOnClickListener {

            if (validateUserInputs()) {
                viewMazaadData()
            }
            Log.d(TAG, "onCreate: " + dropDownETMap.keys.toString())
        }
    }

    private fun viewMazaadData() {
        val userInputs = arrayListOf<MazaadEntry>()
        dropDownETMap.forEach {
            userInputs.add(MazaadEntry(it.key, it.value.editText?.text.toString()))
        }
        userInputs.add(MazaadEntry(getString(R.string.main_category),binding.etMainCategory.editableText.toString()))
        userInputs.add(MazaadEntry(getString(R.string.sub_category),binding.etSubCategory.editableText.toString()))

        startActivity(
            Intent(this, MazaadResultActivity::class.java).putExtra(
                "data",
                MazaadDataModel(userInputs)
            )
        )
    }

    private fun validateUserInputs(): Boolean {
        for (mutableEntry in dropDownETMap) {
            if (mutableEntry.value.editText?.text.toString().isEmpty()) {
                mutableEntry.value.error =
                    getString(R.string.please_specify) + " ${mutableEntry.key}"
                return false
            } else {
                mutableEntry.value.error = null
            }
        }
        return true

    }

    private fun subscribeObserver() {
        lateinit var progressDialog: LoadingProgressDialog

        lifecycleScope.launch {

            viewModel.uiState.collectLatest { uiState ->
                if (uiState.isLoading) {
                    progressDialog = showProgressDialog(getString(R.string.loading_categories))
                }

                uiState.categoriesData?.let {
                    setCategoryDetails(it)

                    progressDialog.dismissProgressDialog()
                }

                if (uiState.errorMessage != null) {
                    Log.d(TAG, "subscribeObserver: " + uiState.errorMessage)
                    progressDialog.dismissProgressDialog()
                    showToast(R.string.no_internt_connection)


                }

            }
        }
        lifecycleScope.launch {

            viewModel.subCategoryPropsUiState.collectLatest { uiState ->

                if (uiState.isLoading) {
                    Log.d(TAG, "subscribeObserver:Loading ")
                    progressDialog =
                        showProgressDialog(getString(R.string.loading_sub_category_data))


                }

                uiState.subCategoryProps?.let {
                    it.data?.forEach {
                        Log.d(TAG, "subscribeObserver: " + it.name)

                    }
                    setUpRestOfDetails(it)
                    delay(500)

                    progressDialog.dismissProgressDialog()

                }

                if (uiState.errorMessage != null) {
                    Log.d(TAG, "subscribeObserver: " + uiState.errorMessage)
                    progressDialog.dismissProgressDialog()

                    showToast(R.string.no_internt_connection)
                }

            }
        }
    }

    private fun setUpRestOfDetails(subCategoryProps: SubCategoryProps) {
        createOptionEditText(subCategoryProps)


    }


    val dropDownETMap = mutableMapOf<String, TextInputLayout>()

    private fun createOptionEditText(subCategoryProps: SubCategoryProps) {
        var bottomSheetSelectionDialog: BottomSheetSelectionDialog? = null
        val mRlayout = binding.etLinear


        dropDownETMap.values.forEach {
            mRlayout.removeView(it)
        }
        dropDownETMap.clear()

        subCategoryProps.data?.forEach { props ->

            val optionsNameList = props.options?.map { option ->
                option.name ?: ""
            }?.toMutableList() ?: arrayListOf()


            val textInputLayout = TextInputLayout(this, null, R.attr.customTextInputStyle)
            val textInputLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )

            textInputLayout.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);

            textInputLayout.setBoxBackgroundColor(
                ContextCompat.getColor(
                    this, android.R.color.white
                )
            );


            val myEditText = TextInputEditText(textInputLayout.context)
            val editTextParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            textInputLayout.layoutParams = textInputLayoutParams
            textInputLayout.addView(myEditText, editTextParams)


//
            myEditText.disable()
            textInputLayout.hint = (props.name)
            val img: Drawable =
                getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24)
            img.setBounds(0, 0, 60, 60)
            myEditText.setCompoundDrawables(null, null, img, null)
            myEditText.setTextColor(Color.BLACK)

            optionsNameList.add(0, OTHER)

            dropDownETMap.putIfAbsent(props.name.toString(), textInputLayout)
            mRlayout.addView(textInputLayout)

            myEditText.setOnClickListener {
                Log.d(TAG, "setUpRestOfDetails: ")
                bottomSheetSelectionDialog = BottomSheetSelectionDialog(
                    this,
                    optionsNameList,
                    object : OnChoiceSelectedListener {

                        override fun onSelect(name: String) {
                            bottomSheetSelectionDialog?.dismiss()
                            if (name.equals(OTHER)) {
                                myEditText.enable()
                                myEditText.setText("")

                                textInputLayout.setHint(R.string.specify_here)
                            } else {
                                myEditText.disable()
                                Log.d(TAG, "onSelect: " + name)
                                myEditText.setText(name)
                                props.selected = name
                            }


                        }

                    })
                bottomSheetSelectionDialog?.show(props.name.toString())
            }

        }
    }


    private fun setCategoryDetails(allCategories: AllCategories) {
        var bottomSheetSelectionDialog: BottomSheetSelectionDialog? = null

        binding.etMainCategory.setOnClickListener {
            bottomSheetSelectionDialog = BottomSheetSelectionDialog(this,
                GetListOfStringFromAllCategoriesUseCase()(allCategories),
                object : OnChoiceSelectedListener {
                    override fun onSelect(name: String) {
                        bottomSheetSelectionDialog?.dismiss()
                        Log.d(TAG, "onSelect: " + name)
                        binding.etMainCategory.setText(name)

                        filterSubCategoriesByCategoryName(name, allCategories)
                        clearSubCategory()
                        clearAllDetails()
                    }

                })
            bottomSheetSelectionDialog?.show(getString(R.string.main_category))
        }
    }


    private fun filterSubCategoriesByCategoryName(
        selectedCategoryName: String, allCategories: AllCategories
    ) {
        var bottomSheetSelectionDialog: BottomSheetSelectionDialog? = null

        binding.etSubCategory.setOnClickListener {
            bottomSheetSelectionDialog = BottomSheetSelectionDialog(this,
                GetListOfSubCategoryByCategoryName()(allCategories, selectedCategoryName),
                object : OnChoiceSelectedListener {
                    override fun onSelect(name: String) {
                        bottomSheetSelectionDialog?.dismiss()
                        Log.d(TAG, "onSelect: " + name)
                        binding.etSubCategory.setText(name)
                        clearProcessType()
                        clearAllDetails()
                        viewModel.onEvent(
                            CreateMazaadDataEvents.GetSubCreateMazaadProps(
                                GetIdOfSubCategoryByName()(
                                    allCategories, selectedCategoryName, name
                                )?.id.toString()
                            )
                        );

                    }

                })
            bottomSheetSelectionDialog?.show(getString(R.string.sub_category))
        }

    }


    fun clearSubCategory() {
        binding.etSubCategory.setText("")
    }

    fun clearProcessType() {
    }

    fun clearAllDetails() {

    }
}