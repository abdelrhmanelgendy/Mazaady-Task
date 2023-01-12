package org.mazaady.com.domain.usecase

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mazaady.com.data.network.entity.category_models.AllCategories
import org.mazaady.com.data.network.entity.category_models.Category
import org.mazaady.com.data.network.entity.category_models.Data
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.Option
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryData
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryProps
import java.util.concurrent.locks.Condition

@RunWith(JUnit4::class)
internal class GetListOfBrandsBySubCategoryPropsTest {

    @Test
    fun `get list of options names with empty list return empty names`() {
        val subCategoryProps = SubCategoryProps(data = arrayListOf())
        val brandsList =
            GetListOfOptionsBySubCategoryProps()(subCategoryProps, "Process  Type")
        Assert.assertEquals(brandsList.size, 0)
    }

    @Test
    fun `get list of options names with null list return empty names`() {
        val subCategoryProps = SubCategoryProps(data = null)
        val namesList =
            GetListOfOptionsBySubCategoryProps()(subCategoryProps, "Process  Type")
        Assert.assertEquals(namesList.size, 0)
    }

    @Test
    fun `get list of process type names with correct list return correct names`() {
        val subCategoryProps = provideSubCategoryProps()
        val options =
            GetListOfOptionsBySubCategoryProps()(subCategoryProps, "Process  Type")
        Assert.assertEquals(options.size, 3)
    }

    @Test
    fun `get list of Brand names with correct list return correct names`() {
        val subCategoryProps = provideSubCategoryProps()
        val options =
            GetListOfOptionsBySubCategoryProps()(subCategoryProps, "Brand")
        Assert.assertEquals(options.size, 3)
    }

    @Test
    fun `get list of Model names with correct list return correct names`() {
        val subCategoryProps = provideSubCategoryProps()
        val options =
            GetListOfOptionsBySubCategoryProps()(subCategoryProps, "Model")
        Assert.assertEquals(options.size, 3)
    }

    @Test

    fun `get list of Type names with correct list return correct names`() {
        val subCategoryProps = provideSubCategoryProps()
        val options =
            GetListOfOptionsBySubCategoryProps()(subCategoryProps, "Type")
        Assert.assertEquals(options.size, 0)
    }

    @Test

    fun `get list of Condition names with correct list return correct names`() {
        val subCategoryProps = provideSubCategoryProps()
        val options =
            GetListOfOptionsBySubCategoryProps()(subCategoryProps, "Condition")
        Assert.assertEquals(options.size, 4)
    }

    private fun provideSubCategoryProps(): SubCategoryProps {
        return SubCategoryProps(
            data = arrayListOf(
                SubCategoryData(
                    name = "Process  Type",
                    options = arrayListOf(
                        Option(
                            name = "Sell",
                        ),
                        Option(
                            name = "Rent",
                        ), Option(
                            name = "Waiver",
                        )
                    )
                ),
                SubCategoryData(
                    name = "Brand",
                    options = arrayListOf(
                        Option(
                            name = "Acabion",
                        ),
                        Option(
                            name = "Access",
                        ), Option(
                            name = "Ace",
                        )
                    )
                ),
                SubCategoryData(
                    name = "Model",
                    options = arrayListOf(
                        Option(
                            name = "Adventure",
                        ),
                        Option(
                            name = "Atv",
                        ), Option(
                            name = "Cruiser",
                        )
                    )
                ),
                SubCategoryData(
                    name = "Type",
                    options = arrayListOf()
                ),
                SubCategoryData(
                    name = "Condition",
                    options = arrayListOf(
                        Option(name = "New"),
                        Option(name = "Used"),
                        Option(name = "Damaged"),
                        Option(name = "Non Operational"),
                    )
                ),
            )
        )

    }
}