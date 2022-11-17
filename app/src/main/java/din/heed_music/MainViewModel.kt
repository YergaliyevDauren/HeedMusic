package din.heed_music

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _isMiniPlayerVisible: Boolean = false
    val isMiniPlayerVisible: Boolean
        get() = _isMiniPlayerVisible
}