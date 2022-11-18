package din.heed_music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _name = MutableLiveData("Iskandar")
    private val _lastname = MutableLiveData("Rasulov")

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastname
}