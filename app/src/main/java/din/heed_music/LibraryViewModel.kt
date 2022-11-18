package din.heed_music

import android.util.Log
import androidx.lifecycle.ViewModel
import din.data.Datasource
import din.model.Album


class LibraryViewModel : ViewModel() {
    private var _dataSet: List<Album> = Datasource().loadSampleAlbums()
    val dataSet: List<Album>
        get() = _dataSet

    override fun onCleared() {
        super.onCleared()
        Log.d("LibraryFragment", "LibraryViewModel destroyed!")
    }

    fun onSongClick() {

    }
}