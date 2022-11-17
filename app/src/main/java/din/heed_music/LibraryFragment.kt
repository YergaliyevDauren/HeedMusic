package din.heed_music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import din.adapter.CarouselAdapter
import din.adapter.LibraryAdapter
import din.data.Datasource

class LibraryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View =  inflater.inflate(R.layout.fragment_library, container, false)
        val myDataset = Datasource().loadSampleAlbums(25).shuffled()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_library)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        recyclerView.adapter = LibraryAdapter(myDataset)
        return view
    }
}