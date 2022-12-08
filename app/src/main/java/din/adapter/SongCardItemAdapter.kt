package din.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import din.database.LibrarySong
import din.heed_music.databinding.SongCardItemBinding

class SongCardItemAdapter(val clickListener: SongCardItemListener) :
    ListAdapter<LibrarySong, SongCardItemAdapter.ViewHolder> (LibrarySongDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

        class ViewHolder private constructor(val binding: SongCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: LibrarySong, clickListener: SongCardItemListener) {
                binding.song = item
                binding.clickListener = clickListener
                binding.executePendingBindings()
            }

            companion object {
                fun from(parent: ViewGroup): ViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = SongCardItemBinding.inflate(layoutInflater, parent, false)
                    return ViewHolder(binding)
                }
            }
        }
}

class LibrarySongDiffCallBack: DiffUtil.ItemCallback<LibrarySong>() {
    override fun areItemsTheSame(oldItem: LibrarySong, newItem: LibrarySong): Boolean {
        return oldItem.songId == newItem.songId
    }
    override fun areContentsTheSame(oldItem: LibrarySong, newItem: LibrarySong): Boolean {
        return oldItem == newItem
    }
}

class SongCardItemListener (
    val clickListener: (songId: Long) -> Unit) {

    fun onClick(song: LibrarySong) = clickListener(song.songId)
}

/*
class ItemOffsetDecoration (
    val context: Context,
    val itemOffset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(itemOffset, itemOffset, itemOffset, itemOffset)
    }

}*/