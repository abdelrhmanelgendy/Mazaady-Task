package org.mazaady.com.presentation.create_mazaad

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.util.LayoutDirection
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.mazaady.com.R
import org.mazaady.com.data.category.dto.AllCategories
import org.mazaady.com.data.subCategory.dto.SubCategoryProps
import org.mazaady.com.databinding.ActivityMainBinding
import org.mazaady.com.domain.category.usecase.GetListOfSheetItemsByCategoryName
import org.mazaady.com.domain.usecase.AddUserInputsWithoutOtherUseCase
import org.mazaady.com.domain.usecase.GetListOfSheetItemsFromAllCategoriesUseCase
import org.mazaady.com.domain.usecase.ValidateUserInputsUseCase
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetSelectionDialog
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetTiles
import org.mazaady.com.presentation.create_mazaad.model.LinearEditeTextItem
import org.mazaady.com.presentation.create_mazaad.viewmodel.CreateMazaadDataEvents
import org.mazaady.com.presentation.create_mazaad.viewmodel.CreateMazaadViewModel
import org.mazaady.com.presentation.dialogs.LoadingProgressDialog
import org.mazaady.com.presentation.mazaad_activity.MazaadActivity
import org.mazaady.com.presentation.mazaad_result_viewer.MazaadResultActivity
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadDataModel
import org.mazaady.com.presentation.mazaad_result_viewer.model.MazaadEntry
import org.mazaady.com.presentation.util.OnChoiceSelectedListener
import org.mazaady.presentation.util.*


@AndroidEntryPoint
class CreateMazaadActivity : AppCompatActivity() {

    companion object {
        const val MAZAAD_DATA = "data"
        const val OTHER = "Other"

    }

    private var options: ArrayList<BottomSheetTiles> = arrayListOf()
     private val viewModel: CreateMazaadViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mRlayout = binding.etLinear
        subscribeObserver()
        viewModel.onEvent(CreateMazaadDataEvents.GetCreateMazaadData);
        listenToSubmitButton()
        binding.mazaadHeader.appBarAction.setOnClickListener {
            startActivity(Intent(this, MazaadActivity::class.java))
        }

    }

    private fun listenToSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            if (validateUserInputs()) {
                viewMazaadData()
            }
        }
        binding.btnClear.setOnClickListener {
            clearAllOptions()
            clearSubCategory()
            dropDownETList.clear()

        }
    }

    private fun viewMazaadData() {
        val userInputs = arrayListOf<MazaadEntry>()
        clearError()
        if(validateCategory( binding.etMainCategory.editableText.toString()))
        {
            binding.etLayoutMainCategory.error = getString(R.string.please_specify)+" the main category"
            return
        }
        if(validateCategory(binding.etSubCategory.editableText.toString()))
        {
            binding.etLayoutSubCategory.error = getString(R.string.please_specify)+" the sub category"
            return
        }

        userInputs.add(
            MazaadEntry(
                getString(R.string.main_category), binding.etMainCategory.editableText.toString()
            )
        )
        userInputs.add(
            MazaadEntry(
                getString(R.string.sub_category), binding.etSubCategory.editableText.toString()
            )
        )

        AddUserInputsWithoutOtherUseCase()(dropDownETList, userInputs)


        startActivity(
            Intent(this, MazaadResultActivity::class.java).putExtra(
                MAZAAD_DATA, MazaadDataModel(userInputs)
            )
        )
    }

    private fun clearError() {
        binding.etLayoutMainCategory.error = null
        binding.etSubCategory.error = null
        binding.etMainCategory.error = null
        binding.etLayoutSubCategory.error = null
    }

    private fun validateCategory(txt: String): Boolean {
        return txt.isEmpty()

    }

    private fun validateUserInputs(): Boolean {
        return ValidateUserInputsUseCase()(dropDownETList, this)

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
                    progressDialog.dismissProgressDialog()
                    showToast(R.string.no_internt_connection)


                }

            }
        }
        lifecycleScope.launch {
            viewModel.subCategoryPropsUiState.collectLatest { uiState ->

                if (uiState.isLoading) {
                    progressDialog =
                        showProgressDialog(getString(R.string.loading_sub_category_data))
                }

                uiState.subCategoryProps?.let {
                    setUpRestOfDetails(it)
                    delay(500)

                    progressDialog.dismissProgressDialog()

                }

                if (uiState.errorMessage != null) {
                    progressDialog.dismissProgressDialog()

                    showToast(R.string.no_internt_connection)
                }

            }
        }
        lifecycleScope.launch {

            viewModel.childOptionsUiState.collectLatest { uiState ->

                if (uiState.isLoading) {
                    progressDialog = showProgressDialog(getString(R.string.loading_options_data))
                }

                uiState.childOptions?.let { childOptions ->
                    delay(500)
                    progressDialog.dismissProgressDialog()
                    if (optionsContainsItem(childOptions.data!!.first().name)) {
                        options.removeAt(currentCLickedIndex + 1)
                    }
                    options.add(
                        currentCLickedIndex + 1, BottomSheetTiles(
                            name = childOptions.data.first().name,
                            items = ArrayList(childOptions.data.first().options.map {
                                BottomSheetItem(
                                    name = it.name,
                                    id = it.id,
                                    parent = it.parent,
                                    child = it.child,
                                    isOtherAdded = false
                                )
                            })
                        )
                    )
                    createOptionEditText(options)

                }

                if (uiState.errorMessage != null) {
                    progressDialog.dismissProgressDialog()
                    showToast(R.string.no_internt_connection)
                }

            }
        }
    }

    /*
     module app
    clean app arch
      */
    private fun optionsContainsItem(name: String): Boolean {
        for (option in options) {
            if (option.name.equals(name)) return true
        }
        return false
    }

    private fun setUpRestOfDetails(subCategoryProps: SubCategoryProps) {
        createOptionEditText(
            ArrayList(subCategoryProps.data!!.map {
                BottomSheetTiles(it.name.toString(), ArrayList(it.options!!.map {
                    BottomSheetItem(
                        name = it.name!!,
                        id = it.id!!,
                        parent = it.parent!!,
                        child = it.child ?: false,
                        isOtherAdded = false
                    )

                }))

            })
        )


    }


    val dropDownETList = mutableListOf<LinearEditeTextItem>()
    var bottomSheetSelectionDialog: BottomSheetSelectionDialog? = null
    lateinit var mRlayout: LinearLayout

    private fun createOptionEditText(bottomSheetItems: ArrayList<BottomSheetTiles>) {
        clearAllOptions()
        this.options = bottomSheetItems;
        dropDownETList.forEach {
            mRlayout.removeView(it.item)
        }
        dropDownETList.clear()

        bottomSheetItems.forEachIndexed { index, props ->

            if (props.name.equals(OTHER)) {
                val editTextParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val textInputLayout = buildUserInputTextInputLayout()
                val myEditText = buildUserInputCustomEditText(textInputLayout.context)

                if (options.get(index).selectedOption.isNotEmpty()) {
                    myEditText.setText(options.get(index).selectedOption)
                }
                textInputLayout.addView(myEditText, editTextParams)
                dropDownETList.add(LinearEditeTextItem(props.name, textInputLayout,parent = props.parent))
                mRlayout.addView(textInputLayout)
                myEditText.addTextChangedListener {
                    options.get(index).selectedOption = it.toString()
                }

                myEditText.showKeyboard()


            } else {
                val editTextParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val textInputLayout = buildTextInputLayout(props.name)
                val myEditText = buildCustomEditText(textInputLayout.context)

                if (options.get(index).selectedOption.isNotEmpty()) {
                    myEditText.setText(options.get(index).selectedOption)
                }
                textInputLayout.addView(myEditText, editTextParams)
                dropDownETList.add(LinearEditeTextItem(props.name, textInputLayout))
                mRlayout.addView(textInputLayout)
                textInputLayout.requestFocus()

                if (!props.items.contains(
                        BottomSheetItem(
                            OTHER, -1, -1, false, isOtherAdded = false
                        )
                    )
                ) {
                    props.items.add(0, BottomSheetItem(OTHER, -1, -1, false, isOtherAdded = false))

                }
                myEditText.setOnClickListener {
                    bottomSheetSelectionDialog = BottomSheetSelectionDialog(this,
                        props.items,
                        object : OnChoiceSelectedListener {
                            override fun onSelect(categoryData: BottomSheetItem) {
                                bottomSheetSelectionDialog?.dismiss()
                                if (categoryData.toString().equals(OTHER)) {
                                    if (!props.isOtherAdded!!) {

                                        currentCLickedIndex = index;
                                        options.get(index).selectedOption = OTHER

                                        options.add(
                                            currentCLickedIndex + 1, BottomSheetTiles(
                                                name = OTHER, items = arrayListOf(), "", parent = options.get(index).name
                                            )
                                        )

                                        createOptionEditText(options)
                                        props.isOtherAdded = true
                                    }


                                } else {
                                    myEditText.setText(categoryData.name)
                                    options.get(index).selectedOption = categoryData.name

                                    if (props.isOtherAdded!!) {
                                        currentCLickedIndex = index;
                                        options.removeAt(
                                            currentCLickedIndex + 1,
                                        )
                                        createOptionEditText(options)
                                        props.isOtherAdded = false
                                    }
                                    if (categoryData.child) {
                                        currentCLickedIndex = index;
                                        createChildOptionForCurrentCategory(categoryData);
                                    }
                                }


                            }

                        })
                    bottomSheetSelectionDialog?.show(props.name)
                }
            }


        }

    }

    private fun buildCustomEditText(
        context: Context,
    ): TextInputEditText {
        val myEditText = TextInputEditText(context)


        myEditText.disable()
        val img: Drawable =
            getResources().getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24)
        img.setBounds(0, 0, 60, 60)
        myEditText.setCompoundDrawables(null, null, img, null)
        myEditText.setTextColor(Color.BLACK)
        myEditText.layoutDirection=LayoutDirection.LTR

        return myEditText
    }

    private fun buildTextInputLayout(
        name: String?
    ): TextInputLayout {

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
        textInputLayout.layoutDirection=LayoutDirection.LTR

        textInputLayout.layoutParams = textInputLayoutParams
        textInputLayout.hint = (name)
        return textInputLayout
    }

    private fun buildUserInputTextInputLayout(
    ): TextInputLayout {

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
        textInputLayout.layoutDirection=LayoutDirection.LTR

        textInputLayout.layoutParams = textInputLayoutParams
        textInputLayout.hint = (getString(R.string.Specify_here))
        return textInputLayout
    }

    private fun buildUserInputCustomEditText(
        context: Context,
    ): TextInputEditText {
        val myEditText = TextInputEditText(context)
        myEditText.setTextColor(Color.BLACK)
        myEditText.imeOptions = EditorInfo.IME_ACTION_NEXT
        myEditText.inputType = InputType.TYPE_CLASS_TEXT
        myEditText.layoutDirection=LayoutDirection.LTR
        return myEditText
    }

    var currentCLickedIndex = -1;

    private fun createChildOptionForCurrentCategory(categoryData: BottomSheetItem) {
        viewModel.onEvent(CreateMazaadDataEvents.GetChildOptions(categoryData.id.toString()));
    }


    private fun setCategoryDetails(allCategories: AllCategories) {
        var bottomSheetSelectionDialog: BottomSheetSelectionDialog? = null

        binding.etMainCategory.setOnClickListener {
            bottomSheetSelectionDialog = BottomSheetSelectionDialog(this,
                GetListOfSheetItemsFromAllCategoriesUseCase()(allCategories),
                object : OnChoiceSelectedListener {
                    override fun onSelect(categoryData: BottomSheetItem) {
                        bottomSheetSelectionDialog?.dismiss()
                        binding.etMainCategory.setText(categoryData.name)

                        filterSubCategoriesByCategoryName(
                            categoryData.name, allCategories
                        )
                        clearSubCategory()
                        clearAllOptions()
                        options.clear()
                        dropDownETList.clear()
                    }

                })
            bottomSheetSelectionDialog?.show(getString(R.string.main_category))
        }
    }

    private fun clearAllOptions() {
        dropDownETList.forEach {
            mRlayout.removeView(it.item)
        }

    }


    private fun filterSubCategoriesByCategoryName(
        selectedCategoryName: String, allCategories: AllCategories
    ) {
        var bottomSheetSelectionDialog: BottomSheetSelectionDialog? = null

        binding.etSubCategory.setOnClickListener {
            bottomSheetSelectionDialog = BottomSheetSelectionDialog(this,
                GetListOfSheetItemsByCategoryName()(allCategories, selectedCategoryName),
                object : OnChoiceSelectedListener {
                    override fun onSelect(categoryData: BottomSheetItem) {
                        bottomSheetSelectionDialog?.dismiss()
                        binding.etSubCategory.setText(categoryData.name)
                        viewModel.onEvent(
                            CreateMazaadDataEvents.GetSubCreateMazaadProps(
                                categoryData.id.toString()
                            )
                        );

                    }

                })
            bottomSheetSelectionDialog?.show(getString(R.string.sub_category))
        }

    }


    fun clearSubCategory() {
        binding.etSubCategory.setText("")
        options.clear()
    }

}