package din

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import din.database.Album
import din.database.Artist
import din.database.LibrarySong
import din.heed_music.database.RecentSearch

@BindingAdapter("songTitle")
fun TextView.setSongTitle(item: LibrarySong?) {
        item?.let { text = item.songTitle }
}

@BindingAdapter("albumTitle")
fun TextView.setAlbumName(item: Album?) {
        item?.let { text = item.albumName }
}

@BindingAdapter("artistName")
fun TextView.setArtistName(item: Artist?) {
        item?.let { text = item.artistName }
}

@BindingAdapter("songArtistText")
fun TextView.setSongArtist(item: LibrarySong?) {
        item?.let { text = item.artistName }
}

@BindingAdapter("albumArtistText")
fun TextView.setAlbumArtist(item: Album?) {
        item?.let { text = item.artistName }
}

@BindingAdapter("songCover")
fun ImageView.setSongCover(item: LibrarySong?) {
        item?.let { load(item.coverPath) }
}

@BindingAdapter("albumCover")
fun ImageView.setAlbumCover(item: Album?) {
        item?.let { load(item.coverPath) }
}

@BindingAdapter("artistImage")
fun ImageView.setArtistImage(item: Artist?) {
        item?.let { load(item.artistImage) }
}

@BindingAdapter("searchText")
fun TextView.setSearchText(item: RecentSearch?) {
        item?.let { text=item.searchText }
}