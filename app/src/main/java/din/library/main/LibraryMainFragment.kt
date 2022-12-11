package din.library.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import din.adapter.AlbumCardItemAdapter
import din.adapter.AlbumCardItemListener
import din.adapter.SongCardItemAdapter
import din.adapter.SongCardItemListener
import din.database.LibraryDatabase
import din.heed_music.R
import din.heed_music.databinding.FragmentLibraryMainBinding

class LibraryMainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentLibraryMainBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_library_main, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        val viewModelFactory = LibraryMainViewModelFactory(dataSource)

        val libraryMainViewModel = ViewModelProvider(this, viewModelFactory).get(LibraryMainViewModel::class.java)
        binding.libMainViewModel = libraryMainViewModel

        val albumCardItemAdapter = AlbumCardItemAdapter(AlbumCardItemListener {
            this.findNavController().navigate(LibraryMainFragmentDirections.actionLibraryMainFragmentToAlbumFragment(it, "LibraryMain"))
        })
        binding.rvRecentlyAdded.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding.rvRecentlyAdded.adapter = albumCardItemAdapter

        libraryMainViewModel.recentSongsByAlbums.observe(viewLifecycleOwner, Observer {
            albumCardItemAdapter.submitList(it)
        })

        val navController = this.findNavController()

        libraryMainViewModel.navigateToSection.observe(viewLifecycleOwner, Observer {
            when(it) {
                "artists" -> navController.navigate(
                    LibraryMainFragmentDirections.actionLibraryMainFragmentToLibraryArtistsFragment())
                "albums" -> navController.navigate(
                    LibraryMainFragmentDirections.actionLibraryMainFragmentToLibraryAlbumsFragment())
                "songs" -> navController.navigate(
                    LibraryMainFragmentDirections.actionLibraryMainFragmentToLibrarySongsFragment())
            }

        })

        return binding.root
    }

}