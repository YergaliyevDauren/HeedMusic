package din.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import din.database.Album
import din.heed_music.databinding.AlbumCardItemBinding
import din.heed_music.databinding.CarouselCardItemBinding


class CarouselCardItemAdapter(val clickListener: CarouselCardItemListener) :
    ListAdapter<Album, CarouselCardItemAdapter.ViewHolder>(LibraryAlbumDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return CarouselCardItemAdapter.ViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder private constructor(val binding: CarouselCardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Album, clickListener: CarouselCardItemListener) {
            binding.album = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CarouselCardItemAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CarouselCardItemBinding.inflate(layoutInflater, parent, false)
                return CarouselCardItemAdapter.ViewHolder(binding)
            }
        }
    }
}

class CarouselCardItemListener (
    val clickListener: (albumId: Long) -> Unit) {
    fun onClick(album: Album) = clickListener(album.albumId)
}