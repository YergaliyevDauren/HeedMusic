package din.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import din.adapter.ListItemListener
import din.adapter.SongListItemAdapter
import din.database.LibraryDatabase
import din.heed_music.databinding.FragmentSearchBinding
import din.search.SearchViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        binding.searchViewModel = viewModel
        val adapter = SongListItemAdapter(ListItemListener { songId -> })
        binding.rvRecentlySearched.adapter = adapter
        adapter.submitList(viewModel.recentlySearched.value)

        return binding.root
    }
}