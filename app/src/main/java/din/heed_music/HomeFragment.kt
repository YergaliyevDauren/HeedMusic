package din.heed_music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import din.adapter.CarouselAdapter
import din.data.Datasource
import din.heed_music.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = DataBindingUtil.setContentView(this, R.layout.fragment_home)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel

        binding.imgProfile.load("https://img1.ak.crunchyroll.com/i/spire2/1d8b407eb8961f96cf0e65136088d5071551823951_full.png")

        val myDataset = Datasource().loadSampleAlbums(15).shuffled()
        val recyclerView = binding.rvCarouselRecently
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView.adapter = CarouselAdapter(myDataset)

        val recyclerView2 = binding.rvCarouselSoundsLikeRecently
        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView2.adapter = CarouselAdapter(myDataset.shuffled())

        val recyclerView3 = binding.rvCarouselTodaysHits
        recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView3.adapter = CarouselAdapter(myDataset.shuffled())
        return binding.root
    }
}