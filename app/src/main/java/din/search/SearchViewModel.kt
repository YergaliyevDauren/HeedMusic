package din.search

import androidx.lifecycle.*
import din.database.LibrarySong
import din.database.LibrarySongsDao
import din.heed_music.database.RecentSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    val dataSource: LibrarySongsDao
) : ViewModel() {
    val recentSearches: LiveData<List<RecentSearch>> = dataSource.getRecentSearches()
    val searchResults = MutableLiveData<List<LibrarySong>?>()

    fun searchForSongs(searchString: String) {
        if(searchString.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                searchResults.postValue(dataSource.getSongsLikePattern("$searchString%"))
            }
        } else {
            searchResults.postValue(null)
        }
    }

    fun insertNewRecentSearch(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.insertRecentSearch(search);
        }
    }
}

class SearchViewModelFactory(
    private val dataSource: LibrarySongsDao) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return SearchViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}