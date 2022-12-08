package din.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import din.database.Artist
import din.heed_music.databinding.ListItemArtistBinding

class ArtistListItemAdapter(val clickListener: LibArtistItemListener) :
    ListAdapter<Artist, ArtistListItemAdapter.ViewHolder>(LibraryArtistsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


    class ViewHolder private constructor(val binding: ListItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist, clickListener: LibArtistItemListener) {
            binding.artist = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemArtistBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class LibraryArtistsDiffCallBack: DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.artistId == newItem.artistId
    }
    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }
}

class LibArtistItemListener(
    val clickListener: (artistId: Long) -> Unit) {
    fun onClick(artist: Artist) = clickListener(artist.artistId)
}