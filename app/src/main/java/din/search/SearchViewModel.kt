package din.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import din.database.LibrarySong
import din.database.LibrarySongsDao

class SearchViewModel(
    dataSource: LibrarySongsDao
) : ViewModel() {
    val recentlySearched: LiveData<List<LibrarySong>> = dataSource.getAllLibSongs()
}