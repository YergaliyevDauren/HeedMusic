package din.heed_music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import din.heed_music.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val navController = findNavController(R.id.nav_host_fragment)
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
                R.id.libraryFragment -> {
                    navController.navigate(R.id.libraryFragment)
                    true
                }
                else -> false
            }
        }

        binding.viewModel = MainViewModel()
    }
}