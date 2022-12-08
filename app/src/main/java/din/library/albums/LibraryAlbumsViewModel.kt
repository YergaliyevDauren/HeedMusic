package din.library.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import din.database.Album
import din.database.LibrarySongsDao

class LibraryAlbumsViewModel(
    val dataSource: LibrarySongsDao
) : ViewModel(){
    val database = dataSource
    val albums: LiveData<List<Album>> = dataSource.getAllAlbums()

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

class LibraryAlbumsViewModelFactory(
    private val dataSource: LibrarySongsDao
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LibraryAlbumsViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return LibraryAlbumsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}