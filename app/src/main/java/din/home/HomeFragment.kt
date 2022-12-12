package din.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import din.adapter.*
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
        binding.imgProfile.load("https://64.media.tumblr.com/6d11589b7c2cd92417cc5555d9d467d0/tumblr_psg6pbcUPt1xa3vq3o5_400.png")

        val carouselAdapter = CarouselCardItemAdapter(CarouselCardItemListener { alubmId -> })
        val carouselAdapter1 = CarouselCardItemAdapter(CarouselCardItemListener { alubmId -> })
        val carouselAdapter2 = CarouselCardItemAdapter(CarouselCardItemListener { alubmId -> })

        binding.rvCarouselRecently.adapter = carouselAdapter
        binding.rvCarouselSoundsLikeRecently.adapter = carouselAdapter1
        binding.rvCarouselTodaysHits.adapter = carouselAdapter2

        homeViewModel.songs.observe(viewLifecycleOwner, Observer {
            carouselAdapter.submitList(it)
            carouselAdapter1.submitList(it.shuffled())
            carouselAdapter2.submitList(it.shuffled())
        })
        return binding.root
    }
}