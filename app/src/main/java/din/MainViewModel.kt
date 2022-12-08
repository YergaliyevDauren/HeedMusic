package din

import android.app.Application
import androidx.lifecycle.*
import din.database.LibrarySong
import din.database.LibrarySongsDao
import kotlinx.coroutines.launch

class MainViewModel (
    val dataSource : LibrarySongsDao
) : ViewModel() {
    private val _isSongSelected = MutableLiveData<Boolean>(false)
    val isSongSelected: LiveData<Boolean> = _isSongSelected

    private val _isPlaying = MutableLiveData<Boolean>(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _currSelectedSong = MutableLiveData<LibrarySong?>()
    val currSelectedSong: LiveData<LibrarySong?> = _currSelectedSong

    fun setCurrentSong(songId : Long) {
        viewModelScope.launch {
            _currSelectedSong.value = dataSource.get(songId)
            if(isSongSelected.value == false) _isSongSelected.value = true
            playSong(songId)
        }
    }

    private fun setIsPlaying(isPlaying: Boolean) {
        _isPlaying.value = isPlaying
    }

    fun playSong(songId: Long) {
        setIsPlaying(true)
    }

    fun resume() {
        setIsPlaying(true)
    }

    fun pause() {
        setIsPlaying(false)
    }

    fun resumeOrPause() {
        if(isPlaying.value == true) {
            pause()
        } else {
            resume()
        }
    }
}

class MainViewModelFactory(
    private val dataSource: LibrarySongsDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            Suppress("UNCHECKED_CAST")
            return MainViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}