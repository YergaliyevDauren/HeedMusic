package din.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import coil.load
import din.adapter.SongCardItemAdapter
import din.adapter.SongCardItemListener
import din.database.LibraryDatabase
import din.heed_music.R
import din.heed_music.databinding.FragmentHomeBinding
import din.home.HomeViewModel
import din.home.HomeViewModelFactory

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        val viewModelFactory = HomeViewModelFactory(dataSource)

        val homeViewModel = ViewModelProvider(this, viewModelFactory).get(
            HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        binding.imgProfile.load("https://img1.ak.crunchyroll.com/i/spire2/1d8b407eb8961f96cf0e65136088d5071551823951_full.png")

        val songCardItemAdapter = SongCardItemAdapter(SongCardItemListener { songId -> })

        binding.rvCarouselRecently.adapter = songCardItemAdapter
        binding.rvCarouselSoundsLikeRecently.adapter = songCardItemAdapter
        binding.rvCarouselTodaysHits.adapter = songCardItemAdapter
        return binding.root
    }
}