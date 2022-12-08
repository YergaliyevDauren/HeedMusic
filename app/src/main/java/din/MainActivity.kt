package din

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import din.database.LibraryDatabase
import din.heed_music.R
import din.heed_music.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dataSource = LibraryDatabase.getInstance(this).librarySongsDao()
        val viewModelFactory = MainViewModelFactory(dataSource)
        val mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNav: BottomNavigationView = binding.bottomNavigation;
        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.searchFragment -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }
                R.id.libraryMainFragment -> {
                    navController.navigate(R.id.libraryMainFragment)
                    true
                }
                else -> false
            }
        }
        binding.viewModel = mainViewModel

        mainViewModel.isSongSelected.observe(this, Observer {
            if(it == true) {
                binding.miniplayer.visibility = View.VISIBLE
            } else {
                binding.miniplayer.visibility = View.GONE
            }
        })
        mainViewModel.currSelectedSong.observe(this, Observer {
            it?.let {
                binding.currSong = it
            }
        })
        mainViewModel.isPlaying.observe(this, Observer {
            if(it == true) {
                binding.icMpPlay.setBackgroundResource(R.drawable.ic_pause)
            } else {
                binding.icMpPlay.setBackgroundResource(R.drawable.ic_play)
            }
        })
    }
}