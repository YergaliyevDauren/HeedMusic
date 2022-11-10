package din.heed_music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.searchFragment -> {true}
                R.id.libraryFragment -> {
                    navController.navigate(R.id.libraryFragment)
                    true}
                else -> false
            }
        }

    }
}