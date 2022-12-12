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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

        val user = Firebase.auth.currentUser
        val uid = user?.uid.toString()
        if(uid == "kfTScnJ5BSgAUxkHopKFmaHh1Ym2") {
            binding.imgProfile.load("https://64.media.tumblr.com/3e5bfdc790ae206c5b6fa158e036b0df/0f37834333ca17c2-2f/s540x810/d5acefe5622029cda5afbf2f59fa071e951beab5.jpg")
            homeViewModel._name.value = "Iskander"
        }else if(uid == "OBVt1PmrEcRlMLAeGJr7BSLlYZy2") {
            binding.imgProfile.load("https://i.pinimg.com/564x/5a/0d/aa/5a0daac117a8bfb3ab3ae1f00dff8ae5.jpg")
            homeViewModel._name.value = "Nurkhat"
        }else if(uid == "cdB3lOGUSYfu0xJN2jT4kYUO9G23") {
            binding.imgProfile.load("https://media.newyorker.com/photos/5db5bbea0e8f690008cf0864/2:2/w_1136,h_1136,c_limit/Battan-Kanye.jpg")
            homeViewModel._name.value = "Dauren"
        }

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