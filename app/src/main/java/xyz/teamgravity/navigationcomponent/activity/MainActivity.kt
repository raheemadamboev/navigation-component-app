package xyz.teamgravity.navigationcomponent.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import xyz.teamgravity.navigationcomponent.NavGraphDirections
import xyz.teamgravity.navigationcomponent.R
import xyz.teamgravity.navigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)

            // find nav controller
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.findNavController()

            // hide back button from top level fragments
            appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.searchFragment), binding.drawerLayout)

            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController, appBarConfiguration)

            bottomNavigation.setupWithNavController(navController)
            navigationDrawer.setupWithNavController(navController)

            // hide bottom navigation
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.termsFragment, R.id.settingsFragment ->
                        bottomNavigation.visibility = View.GONE
                    else ->
                        bottomNavigation.visibility = View.VISIBLE
                }
            }
        }
    }

    // in order to respond back button in toolbar from fragment
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    // to navigate fragments in menu with nav controller
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.termsAndConditions) {
            val action = NavGraphDirections.actionGlobalTermsFragment()
            navController.navigate(action)
            true
        } else {
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }
}