package din.library.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import din.MainViewModel
import din.adapter.ListItemListener
import din.adapter.SongListItemAdapter
import din.database.LibraryDatabase
import din.heed_music.R
import din.heed_music.databinding.FragmentAlbumBinding
import kotlinx.coroutines.async

class AlbumFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentAlbumBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        val args: AlbumFragmentArgs by navArgs()
        val viewModelFactory = LibraryAlbumViewModelFactory(dataSource, args.albumId)

        val libAlbumViewModel = ViewModelProvider(this, viewModelFactory)[LibraryAlbumViewModel::class.java]
        binding.albumViewModel = libAlbumViewModel

        val songListItemAdapter = SongListItemAdapter(ListItemListener {
            val mainViewModel: MainViewModel by activityViewModels()
            lifecycleScope.async {
                mainViewModel.playSong(dataSource.get(it))
            }
        })
        binding.rvAlbumSongs.adapter = songListItemAdapter

        libAlbumViewModel.albumSongs.observe(viewLifecycleOwner, Observer {
            songListItemAdapter.submitList(it)
        })
        libAlbumViewModel.album.observe(viewLifecycleOwner, Observer {
            binding.album = it
        })
        libAlbumViewModel.navigateBack.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                when(args.openedFrom) {
                    "LibraryMain" -> this.findNavController().navigate(
                        AlbumFragmentDirections.actionAlbumFragmentToLibraryMainFragment())
                    "LibraryAlbums" -> this.findNavController().navigate(
                        AlbumFragmentDirections.actionAlbumFragmentToLibraryAlbumsFragment())
                }
                libAlbumViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}