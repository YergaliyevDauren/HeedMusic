package din.library.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import din.adapter.ArtistListItemAdapter
import din.adapter.LibArtistItemListener
import din.database.LibraryDatabase
import din.heed_music.R
import din.heed_music.databinding.FragmentLibraryArtistsBinding

class LibraryArtistsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentLibraryArtistsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_library_artists, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        val viewModelFactory = LibraryArtistsViewModelFactory(dataSource)

        val libArtistsViewModel = ViewModelProvider(this, viewModelFactory)[LibraryArtistsViewModel::class.java]
        binding.libArtistsViewModel = libArtistsViewModel

        val artistListItemAdapter = ArtistListItemAdapter(LibArtistItemListener {

        })
        binding.rvLibArtists.adapter = artistListItemAdapter

        libArtistsViewModel.lib_artists.observe(viewLifecycleOwner, Observer {
            artistListItemAdapter.submitList(it)
        })

        libArtistsViewModel.navigateToLibraryMain.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                this.findNavController().navigate(
                    LibraryArtistsFragmentDirections.actionLibraryArtistsFragmentToLibraryMainFragment())
                    libArtistsViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}