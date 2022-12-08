package din.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import din.database.LibrarySong
import din.heed_music.databinding.SongListItemBinding

class SongListItemAdapter(val clickListener: ListItemListener) :
    ListAdapter<LibrarySong, SongListItemAdapter.ViewHolder> (LibrarySongDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


    class ViewHolder private constructor(val binding: SongListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LibrarySong, clickListener: ListItemListener) {
            binding.song = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SongListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ListItemListener (
    val clickListener: (songId: Long) -> Unit) {
    fun onClick(song: LibrarySong) = clickListener(song.songId)
}