package din.library.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import din.database.Album
import din.database.LibrarySong
import din.database.LibrarySongsDao

class LibraryAlbumViewModel(
    val dataSource: LibrarySongsDao, val albumId: Long) : ViewModel() {

    val database = dataSource
    val album: LiveData<Album> = dataSource.getAlbumById(albumId)
    val albumSongs: LiveData<List<LibrarySong>> = dataSource.getAlbumSongs(albumId)

    private val _navigateToLibraryAlbums = MutableLiveData<Boolean?>()
    val navigateToLibraryAlbums: LiveData<Boolean?>
        get() = _navigateToLibraryAlbums

    fun navigateBackToLibraryAlbums() {
        _navigateToLibraryAlbums.value = true
    }

    fun doneNavigating() {
        _navigateToLibraryAlbums.value = null
    }
}

class LibraryAlbumViewModelFactory(
    private val dataSource: LibrarySongsDao,
    private val albumId: Long
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LibraryAlbumViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return LibraryAlbumViewModel(dataSource, albumId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

