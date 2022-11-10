package din.heed_music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import din.adapter.CarouselAdapter
import din.data.Datasource

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        val myDataset = Datasource().loadSampleAlbums()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_carousel_recently)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView.adapter = CarouselAdapter(myDataset)

        val recyclerView2 = view.findViewById<RecyclerView>(R.id.rv_carousel_sounds_like_recently)
        recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView2.adapter = CarouselAdapter(myDataset)

        val recyclerView3 = view.findViewById<RecyclerView>(R.id.rv_carousel_todays_hits)
        recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView3.adapter = CarouselAdapter(myDataset)
        return view
    }
}