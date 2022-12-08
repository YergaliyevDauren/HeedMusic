package din.library.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import din.database.LibrarySongsDao
import din.library.main.LibraryMainViewModel

class LibraryMainViewModelFactory(
    private val dataSource: LibrarySongsDao
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LibraryMainViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return LibraryMainViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}