package din.library.songs

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
import androidx.recyclerview.widget.LinearLayoutManager
import din.adapter.ListItemListener
import din.adapter.SongListItemAdapter
import din.database.LibraryDatabase
import din.MainViewModel
import din.heed_music.R
import din.heed_music.databinding.FragmentLibrarySongsBinding

class LibrarySongsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentLibrarySongsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_library_songs, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = LibraryDatabase.getInstance(application).librarySongsDao()
        val viewModelFactory = LibrarySongsViewModelFactory(dataSource, application)

        val libSongsViewModel = ViewModelProvider(this, viewModelFactory)[LibrarySongsViewModel::class.java]
        binding.libSongsViewmodel = libSongsViewModel

        val songListItemAdapter = SongListItemAdapter(ListItemListener {
            val mainViewModel: MainViewModel by activityViewModels()
            mainViewModel.play(it)
        })
        binding.rvLibSonsgs.layoutManager = LinearLayoutManager(context)
        binding.rvLibSonsgs.adapter = songListItemAdapter

        libSongsViewModel.songs.observe(viewLifecycleOwner, Observer {
            songListItemAdapter.submitList(it)
        })

        libSongsViewModel.navigateToLibraryMain.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                this.findNavController().navigate(
                    LibrarySongsFragmentDirections.actionLibrarySongsFragmentToLibraryMainFragment())
                libSongsViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}