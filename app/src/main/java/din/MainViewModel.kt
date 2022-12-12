package din

import android.app.Application
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.database.core.RepoManager.resume
import din.database.LibrarySong
import din.database.LibrarySongsDao
import kotlinx.coroutines.*

class MainViewModel (
    val dataSource : LibrarySongsDao
) : ViewModel() {
    val mediaPlayer = MediaPlayer()
    var length: Int = 0
    var currSongLink: String? = null

    init {
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA).build())
        mediaPlayer.isLooping = false
    }

    private val _isSongSelected = MutableLiveData<Boolean>(false)
    val isSongSelected: LiveData<Boolean> = _isSongSelected

    private val _isPlaying = MutableLiveData<Boolean>(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _currSelectedSong = MutableLiveData<LibrarySong?>()
    val currSelectedSong: LiveData<LibrarySong?> = _currSelectedSong

    fun setCurrentSong(songId : Long) {

    }

    private fun setIsPlaying(isPlaying: Boolean) {
        _isPlaying.value = isPlaying
    }

    private fun getSong(songId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _currSelectedSong.postValue(dataSource.get(songId))
        }
    }

    fun play(songId: Long) {
        if(_currSelectedSong.value == null || songId != _currSelectedSong.value?.songId) {
            getSong(songId)
            Log.d("MainViewModel", "${_currSelectedSong.value?.albumName}")
        }
        if(currSongLink != _currSelectedSong.value?.songLink) {
            currSongLink = _currSelectedSong.value?.songLink
            mediaPlayer.reset()
            mediaPlayer.setDataSource(currSongLink)
            mediaPlayer.prepare()
        }
        mediaPlayer.start()

        if(isSongSelected.value == false) _isSongSelected.value = true
        setIsPlaying(true)
    }

    fun pause() {
        setIsPlaying(false)
        mediaPlayer.pause()
        length = mediaPlayer.currentPosition
    }

    fun resumeOrPause(songId: Long) {
        if(isPlaying.value == true) {
            Log.d("MainViewModel", "isPlaying = true")
            pause()
        } else {
            Log.d("MainViewModel", "isPlaying = false")
            play(songId)
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