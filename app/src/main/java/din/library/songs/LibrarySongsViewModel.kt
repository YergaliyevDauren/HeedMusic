package din.library.songs

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import din.database.LibrarySong
import din.database.LibrarySongsDao
import kotlinx.coroutines.launch

class LibrarySongsViewModel (
    val dataSource : LibrarySongsDao,
    application: Application
) : ViewModel()
{
    val database = dataSource
    val songs: LiveData<List<LibrarySong>> = dataSource.getAllLibSongs()

    private val _navigateToLibraryMain = MutableLiveData<Boolean?>()
    val navigateToLibraryMain: LiveData<Boolean?>
        get() = _navigateToLibraryMain

    fun navigateBackToLibrary() {
        _navigateToLibraryMain.value = true
    }

    fun doneNavigating() {
        _navigateToLibraryMain.value = null
    }

    fun playSongs() {

    }

    fun shuffleSongs() {

    }

    fun insertSong(song: LibrarySong) {
        viewModelScope.launch {
            database.insert(song)
        }
    }
}