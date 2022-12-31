import androidx.recyclerview.widget.DiffUtil

typealias itemsData =List<String>
class NamesDiffUtils constructor(
    private val oldList: itemsData,
    private val updatedList: itemsData
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = updatedList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList.get(oldItemPosition)).equals (updatedList.get(newItemPosition))
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            (!oldList.get(oldItemPosition).equals(updatedList.get(newItemPosition))) -> false
              else -> true
        }
    }
}