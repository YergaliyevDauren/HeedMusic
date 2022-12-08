package din.library.artists

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import din.database.Artist
import din.database.LibrarySongsDao

class LibraryArtistsViewModel(
    val dataSource : LibrarySongsDao
) : ViewModel() {
    val lib_artists: LiveData<List<Artist>> = dataSource.getAllLibArtists()

    private val _navigateToLibraryMain = MutableLiveData<Boolean?>()
    val navigateToLibraryMain: LiveData<Boolean?>
        get() = _navigateToLibraryMain

    fun navigateBackToLibrary() {
        _navigateToLibraryMain.value = true
    }

    fun doneNavigating() {
        _navigateToLibraryMain.value = null
    }
}

class LibraryArtistsViewModelFactory(
    private val dataSource: LibrarySongsDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LibraryArtistsViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return LibraryArtistsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}