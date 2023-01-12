import androidx.recyclerview.widget.DiffUtil
import org.mazaady.com.data.network.entity.category_models.subcategory_props_model.SubCategoryData
import org.mazaady.com.presentation.bottom_sheet_dialog.BottomSheetItem

typealias itemsData =List<BottomSheetItem>
class NamesDiffUtils constructor(
    private val oldList: itemsData,
    private val updatedList: itemsData
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = updatedList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList.get(oldItemPosition).name).equals (updatedList.get(newItemPosition).name)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            (!oldList.get(oldItemPosition).name.equals(updatedList.get(newItemPosition).name)) -> false
              else -> true
        }
    }
}