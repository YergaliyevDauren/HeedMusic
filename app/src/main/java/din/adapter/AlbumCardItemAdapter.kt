package din.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import din.database.Album
import din.heed_music.databinding.AlbumCardItemBinding


class AlbumCardItemAdapter(val clickListener: AlbumCardItemListener) :
    ListAdapter<Album, AlbumCardItemAdapter.ViewHolder>(LibraryAlbumDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AlbumCardItemAdapter.ViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: AlbumCardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album, clickListener: AlbumCardItemListener) {
            binding.album = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AlbumCardItemAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlbumCardItemBinding.inflate(layoutInflater, parent, false)
                return AlbumCardItemAdapter.ViewHolder(binding)
            }
        }
    }
}

class LibraryAlbumDiffCallBack: DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.albumId == newItem.albumId
    }
    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}

class AlbumCardItemListener (
    val clickListener: (albumId: Long) -> Unit) {
    fun onClick(album: Album) = clickListener(album.albumId)
}