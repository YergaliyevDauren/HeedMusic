package din.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import din.MainViewModel
import din.adapter.ListItemListener
import din.adapter.SearchItemAdapter
import din.adapter.SearchItemListener
import din.adapter.SongListItemAdapter
import din.database.LibraryDatabase
import din.heed_music.R
import din.heed_music.databinding.FragmentSearchBinding
import din.search.SearchViewModel

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        val viewModelFactory = SearchViewModelFactory(dataSource)

        val searchViewModel = ViewModelProvider(this, viewModelFactory).get(
            SearchViewModel::class.java)
        binding.searchViewModel = searchViewModel

        val searchEditText = binding.etSearch
        setOnEditorActionListener(context,searchViewModel,searchEditText)
        val searchResultsAdapter = SongListItemAdapter(ListItemListener {
            val mainViewModel: MainViewModel by activityViewModels()
            mainViewModel.play(it)
        })
        val recentSearchesAdapter = SearchItemAdapter(SearchItemListener {
            searchEditText.setText(it, TextView.BufferType.EDITABLE)
            searchEditText.setSelection(searchEditText.length())
        })
        binding.rvRecentlySearched.adapter = recentSearchesAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("Search", "AfterTextChanged")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchViewModel.searchForSongs(s.toString())
                Log.d("Search", "OnTextChanged")
            }
        })

        searchViewModel.searchResults.observe(viewLifecycleOwner, Observer {
            it?.let {
                searchResultsAdapter.submitList(it)
                binding.rvRecentlySearched.adapter = searchResultsAdapter

                binding.txtSubTitle.text = resources.getString(R.string.search_results)
            } ?: run {
                binding.rvRecentlySearched.adapter = recentSearchesAdapter
                binding.txtSubTitle.text = resources.getString(R.string.recently_searched)
            }
        })

        searchViewModel.recentSearches.observe(viewLifecycleOwner, Observer {
            it?.let { recentSearchesAdapter.submitList(it) }
        })


        return binding.root
    }

    private fun setOnEditorActionListener(context: Context?, viewModel: SearchViewModel, editText: EditText) {
        editText.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.insertNewRecentSearch(editText.text.toString())
                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.rootView.windowToken, 0)
                return@OnEditorActionListener true
            }
            false
        })
    }

}