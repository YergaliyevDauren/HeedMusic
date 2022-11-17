package din.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import din.heed_music.R
import din.model.Album

class CarouselAdapter(
    private val dataSet: List<Album>
) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val txt_albumTitle : TextView
        val txt_artist : TextView
        val img_albumCover : ImageView
        init {
            txt_albumTitle = view.findViewById(R.id.txt_albumName)
            txt_artist = view.findViewById(R.id.txt_artist)
            img_albumCover = view.findViewById(R.id.img_albumCover)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.carousel_album_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_albumTitle.text = dataSet[position].title
        holder.txt_artist.text = dataSet[position].artist
        holder.img_albumCover.load(dataSet[position].coverPath)
    }

    override fun getItemCount() = dataSet.size
}