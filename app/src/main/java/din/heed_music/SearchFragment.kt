package din.heed_music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import din.adapter.LibraryAdapter
import din.data.Datasource
import din.heed_music.R

class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_search, container, false)
        val datasetRecentSearches = Datasource().loadSampleRecentlySearched()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_recently_searched)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        recyclerView.adapter = LibraryAdapter(datasetRecentSearches)
        return view
    }
}