package din.library.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import din.adapter.AlbumCardItemAdapter
import din.adapter.AlbumCardItemListener
import din.database.LibraryDatabase
import din.heed_music.R
import din.heed_music.databinding.FragmentLibraryAlbumsBinding

class LibraryAlbumsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentLibraryAlbumsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_library_albums, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        val viewModelFactory = LibraryAlbumsViewModelFactory(dataSource)

        val libAlbumsViewModel = ViewModelProvider(this, viewModelFactory)[LibraryAlbumsViewModel::class.java]
        binding.libAlbumsViewmodel = libAlbumsViewModel

        val albumCardItemAdapter = AlbumCardItemAdapter(AlbumCardItemListener {
            this.findNavController().navigate(LibraryAlbumsFragmentDirections.actionLibraryAlbumsFragmentToAlbumFragment(it,"LibraryAlbums"))
        })
        binding.rvLibAlbums.adapter = albumCardItemAdapter

        libAlbumsViewModel.albums.observe(viewLifecycleOwner, Observer {
            albumCardItemAdapter.submitList(it)
        })
        libAlbumsViewModel.navigateToLibraryMain.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                this.findNavController().navigate(
                    LibraryAlbumsFragmentDirections.actionLibraryAlbumsFragmentToLibraryMainFragment())
                libAlbumsViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}