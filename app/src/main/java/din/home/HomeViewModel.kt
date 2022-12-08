package din.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import din.database.LibrarySong
import din.database.LibrarySongsDao

class HomeViewModel (
    dataSource: LibrarySongsDao
) : ViewModel() {
    val songs: LiveData<List<LibrarySong>> = dataSource.getAllLibSongs()

    private val _name = MutableLiveData("Iskandar")
    private val _lastname = MutableLiveData("Rasulov")

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastname
}

class HomeViewModelFactory(
    private val dataSource: LibrarySongsDao
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return HomeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}