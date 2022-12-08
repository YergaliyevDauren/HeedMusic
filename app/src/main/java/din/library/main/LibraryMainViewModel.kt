package din.library.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import din.database.LibrarySong
import din.database.LibrarySongsDao
import kotlinx.coroutines.launch

class LibraryMainViewModel(
    val dataSource : LibrarySongsDao
) : ViewModel()
{
    val database = dataSource
    val songs: LiveData<List<LibrarySong>> = dataSource.getAllLibSongs()

    private val _navigateToSection = MutableLiveData<String?>()
    val navigateToSection: LiveData<String?>
        get() = _navigateToSection

    fun doneNavigating() {
        _navigateToSection.value = null
    }

    fun onSelectingSection(sectionId: Int) {
        viewModelScope.launch {
            when(sectionId) {
                1 -> _navigateToSection.value = "artists"
                2 -> _navigateToSection.value = "albums"
                3 -> _navigateToSection.value = "songs"
            }
        }
    }

    fun insertSong(song: LibrarySong) {
        viewModelScope.launch {
            database.insert(song)
        }
    }


}

