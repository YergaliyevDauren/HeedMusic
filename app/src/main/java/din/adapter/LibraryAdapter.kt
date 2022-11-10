package din.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import din.heed_music.R
import din.model.Album

class LibraryAdapter(
    private val dataSet: List<Album>
): RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tv_lib_item_song : TextView
        val tv_lib_item_artist : TextView
        val img_lib_item : ImageView
        init {
            tv_lib_item_song = view.findViewById(R.id.tv_lib_item_song)
            tv_lib_item_artist = view.findViewById(R.id.tv_lib_item_artist)
            img_lib_item = view.findViewById(R.id.img_lib_item)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.library_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_lib_item_song.text = dataSet[position].title
        holder.tv_lib_item_artist.text = dataSet[position].artist
//        val imgBitmap = BitmapFactory.decodeFile(dataSet[position].coverPath)
//        holder.img_albumCover.setImageBitmap(imgBitmap)
    }

    override fun getItemCount() = dataSet.size
}