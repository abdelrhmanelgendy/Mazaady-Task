package org.mazaady.com.presentation.create_mazaad

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps
import org.mazaady.com.databinding.ActivityMainBinding
import org.mazaady.com.domain.usecase.GetListOfSheetItemsByCategoryName
import org.mazaady.com.domain.usecase.GetListOfSheetItemsFromAllCategoriesUseCase
import org.mazaady.com.presentation.CreateMazaadDataEvents
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetSelectionDialog
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetTiles
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
    }

    private var options: ArrayList<BottomSheetTiles> = arrayListOf()
    private val TAG = "MainActivityTAG"
    private val viewModel: CreateMazaadViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val OTHER = "Other"

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
    }

    private fun viewMazaadData() {
        val userInputs = arrayListOf<MazaadEntry>()
        dropDownETMap.forEach {
            userInputs.add(MazaadEntry(it.name, it.item.editText?.text.toString()))
        }
        userInputs.add(
            MazaadEntry(
                getString(R.string.main_category),
                binding.etMainCategory.editableText.toString()
            )
        )
        userInputs.add(
            MazaadEntry(
                getString(R.string.sub_category),
                binding.etSubCategory.editableText.toString()
            )
        )

        startActivity(
            Intent(this, MazaadResultActivity::class.java).putExtra(
                MAZAAD_DATA,
                MazaadDataModel(userInputs)
            )
        )
    }

    private fun validateUserInputs(): Boolean {
        for (mutableEntry in dropDownETMap) {
            if (mutableEntry.item.editText?.text.toString().isEmpty()) {
                mutableEntry.item.error =
                    getString(R.string.please_specify) + " ${mutableEntry.name}"
                return false
            } else {
                mutableEntry.item.error = null
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
        lifecycleScope.launch {

            viewModel.childOptionsUiState.collectLatest { uiState ->

                if (uiState.isLoading) {
                    progressDialog =
                        showProgressDialog(getString(R.string.loading_options_data))
                }

                uiState.childOptions?.let { childOptions ->
                    delay(500)
                    progressDialog.dismissProgressDialog()
                    Log.d(TAG, "subscribeObserver: " + options.size)
                    if (optionsContainsItem(childOptions.data!!.first().name)) {
                        options.removeAt(currentCLickedIndex + 1)

                    }
                    options.add(
                        currentCLickedIndex + 1,
                        BottomSheetTiles(
                            name = childOptions.data.first().name, items =
                            ArrayList(childOptions.data.first().options.map {
                                BottomSheetItem(
                                    name = it.name,
                                    id = it.id,
                                    parent = it.parent,
                                    child = it.child, isOtherAdded = false
                                )
                            })
                        )
                    )
                    Log.d(TAG, "subscribeObserver: " + options.size)

                    createOptionEditText(options)

                }

                if (uiState.errorMessage != null) {
                    Log.d(TAG, "subscribeObserver: " + uiState.errorMessage)
                    progressDialog.dismissProgressDialog()

                    showToast(R.string.no_internt_connection)
                }

            }
        }
    }
/*
handle names in the data viewer
enhance main activity
module app
clean app arch
on enter remove hint
 */
    private fun optionsContainsItem(name: String): Boolean {
        for (option in options) {
            if (option.name.equals(name))
                return true
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
                        child = it.child ?: false, isOtherAdded = false
                    )

                }))

            })
        )


    }


    val dropDownETMap = mutableListOf<LinearEditeTextItem>()
    var bottomSheetSelectionDialog: BottomSheetSelectionDialog? = null
    lateinit var mRlayout: LinearLayout

    private fun createOptionEditText(bottomSheetItems: ArrayList<BottomSheetTiles>) {
        clearAllOptions()
        this.options = bottomSheetItems;
        dropDownETMap.forEach {
            mRlayout.removeView(it.item)
        }
        dropDownETMap.clear()

        bottomSheetItems.forEachIndexed { index, props ->

            if (props.name.equals(OTHER)) {
                val editTextParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val textInputLayout = buildUserInputTextInputLayout(this)
                val myEditText = buildUserInputCustomEditText(textInputLayout.context)

                if (options.get(index).selectedOption.isNotEmpty()) {
                    myEditText.setText(options.get(index).selectedOption)
                }
                textInputLayout.addView(myEditText, editTextParams)
                dropDownETMap.add(LinearEditeTextItem(props.name, textInputLayout))
                mRlayout.addView(textInputLayout)
                myEditText.addTextChangedListener {
                    options.get(index).selectedOption=it.toString()
                }

                myEditText.showKeyboard()



            }
            else {
                val editTextParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val textInputLayout = buildTextInputLayout(this, props.name)
                val myEditText = buildCustomEditText(textInputLayout.context)

                if (options.get(index).selectedOption.isNotEmpty()) {
                    myEditText.setText(options.get(index).selectedOption)
                }
                textInputLayout.addView(myEditText, editTextParams)
                dropDownETMap.add(LinearEditeTextItem(props.name.toString(), textInputLayout))
                mRlayout.addView(textInputLayout)
                Log.d(TAG, "createOptionEditText: "+props.items)
                textInputLayout.requestFocus()


                if (!props.items.contains(
                        BottomSheetItem(
                            OTHER,
                            -1,
                            -1,
                            false,
                            isOtherAdded = false
                        )
                    )
                ) {
                    props.items.add(0, BottomSheetItem(OTHER, -1, -1, false, isOtherAdded = false))

                }
                myEditText.setOnClickListener {
                    bottomSheetSelectionDialog = BottomSheetSelectionDialog(
                        this, props.items,
                        object : OnChoiceSelectedListener {
                            override fun onSelect(categoryData: BottomSheetItem) {
                                bottomSheetSelectionDialog?.dismiss()
                                if (categoryData.toString().equals(OTHER)) {
                                    if (!props.isOtherAdded!!) {

                                        currentCLickedIndex = index;
                                        options.get(index).selectedOption = "Other"

                                        options.add(
                                            currentCLickedIndex + 1,
                                            BottomSheetTiles(
                                                name = OTHER,
                                                items = arrayListOf(), ""
                                            )
                                        )
                                        Log.d(TAG, "subscribeObserver: " + options.size)

                                        createOptionEditText(options)
                                        props.isOtherAdded = true
                                    }


                                } else {
                                    Log.d(TAG, "onSelect1212: "+props.isOtherAdded.toString())
                                    myEditText.setText(categoryData.name)
                                    options.get(index).selectedOption = categoryData.name

                                    if (props.isOtherAdded!!) {

                                        currentCLickedIndex = index;
                                        options.removeAt(
                                            currentCLickedIndex+1,
                                            )

                                        Log.d(TAG, "subscribeObserver:2121 " + options.size)

                                        createOptionEditText(options)
                                        props.isOtherAdded = false
                                    }
                                    if (categoryData.child) {
                                        currentCLickedIndex = index;
                                        createChildOptionForCurrentCategory(categoryData, index);
                                    }
                                }


                            }

                        })
                    bottomSheetSelectionDialog?.show(props.name.toString())
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
        return myEditText
    }

    private fun buildTextInputLayout(
        context: Context,
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
        textInputLayout.layoutParams = textInputLayoutParams
        textInputLayout.hint = (name)
        return textInputLayout
    }

    private fun buildUserInputTextInputLayout(
        context: Context,
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
        textInputLayout.layoutParams = textInputLayoutParams
        textInputLayout.hint = ("Specify here")
        return textInputLayout
    }

    private fun buildUserInputCustomEditText(
        context: Context,
    ): TextInputEditText {
        val myEditText = TextInputEditText(context)
        myEditText.setTextColor(Color.BLACK)
        myEditText.hint="From User"
        myEditText.imeOptions= EditorInfo.IME_ACTION_NEXT
        myEditText.inputType= InputType.TYPE_CLASS_TEXT
        return myEditText
    }

    var currentCLickedIndex = -1;

    private fun createChildOptionForCurrentCategory(categoryData: BottomSheetItem, index: Int) {
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
                            categoryData.name.toString(),
                            allCategories
                        )
                        clearSubCategory()
                        clearAllOptions()
                    }

                })
            bottomSheetSelectionDialog?.show(getString(R.string.main_category))
        }
    }

    private fun clearAllOptions() {
        dropDownETMap.forEach {
            mRlayout.removeView(it.item)
        }
//        options.clear()

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
                        Log.d(TAG, "onSelect: " + categoryData)
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