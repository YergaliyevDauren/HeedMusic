package din.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import din.heed_music.database.RecentSearch
import din.heed_music.databinding.SearchItemBinding


class SearchItemAdapter (val clickListener: SearchItemListener) :
    ListAdapter<RecentSearch, SearchItemAdapter.ViewHolder>(SearchItemDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


    class ViewHolder private constructor(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecentSearch, clickListener: SearchItemListener) {
            binding.searchItem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SearchItemDiffCallBack: DiffUtil.ItemCallback<RecentSearch>() {
    override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
        return oldItem.searchId == newItem.searchId
    }
    override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
        return oldItem == newItem
    }
}
class SearchItemListener (
    val clickListener: (searchText: String) -> Unit) {
    fun onClick(searchItem: RecentSearch) = clickListener(searchItem.searchText)
}
