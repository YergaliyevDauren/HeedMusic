package din.library.songs

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import din.database.LibrarySongsDao
import din.library.songs.LibrarySongsViewModel

class LibrarySongsViewModelFactory(
    private val dataSource: LibrarySongsDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LibrarySongsViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return LibrarySongsViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}