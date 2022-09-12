package com.overcom.bananaapp9.ui.view.modules

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.overcom.bananaapp9.R
import com.overcom.bananaapp9.core.ApiAdapter
import com.overcom.bananaapp9.data.model.Logout
import com.overcom.bananaapp9.data.network.ApiService
import com.overcom.bananaapp9.databinding.ActivityHomeBinding
import com.overcom.bananaapp9.io.dataacces.BananaAppAplication.Companion.preferences
import com.overcom.bananaapp9.ui.view.modules.home.HomeFragment
import com.overcom.bananaapp9.ui.viewmodel.ThirdsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: ThirdsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            this.let {
                ViewModelProvider(it)[ThirdsViewModel::class.java]
            }

        viewModel._activateFilter.value = false

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

       /* binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController= findNavController(R.id.nav_host_fragment_content_home)
        val viewDrawer = navView.getHeaderView(0)
        val avatar = viewDrawer.findViewById<ImageView>(R.id.avatarView)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_thirds,
                R.id.nav_thirds_detail,
                R.id.Logout
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        viewDrawer.findViewById<TextView>(R.id.UsersView).text = preferences.getUserName()
        viewDrawer.findViewById<TextView>(R.id.orgView).text = preferences.getOrgText()
        viewDrawer.findViewById<TextView>(R.id.worksapaceView).text = preferences.getWorkspace()
        Glide.with(avatar.context).load(preferences.getUserImage())
            .placeholder(R.drawable.ic_thirds).into(avatar)
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_thirds, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.svThirds).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            isQueryRefinementEnabled = true
        }

        return true
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)

        val menuItem = menu.findItem(R.id.svThirds)
        val searchView = menuItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {

                viewModel._filter.value = query
                viewModel.thirdsCall(typeThirds = "customer_prospect_archivados")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
            menu.findItem(R.id.change_organization).isVisible = true

            viewModel.activateFilter.observe(this) { show ->
                menu.findItem(R.id.fvThirds).isVisible = show
                menu.findItem(R.id.svThirds).isVisible = show
            }
        return super.onPrepareOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.change_organization -> {
            val viewOrg = layoutInflater.inflate(R.layout.organizations, null)
            preferences.saveFirstRun(true)
            HomeFragment.dialogOrganization(viewOrg, this)
            preferences.saveFirstRun(false)
            true
        }

        R.id.fvThirds -> {
            viewModel.filterThirds(this)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

   /* override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Logout -> {
                Toast.makeText(this, "Publication", Toast.LENGTH_SHORT).show()
                Log.i("MainActivity", "Sign out clicked!")
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }*/

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService: ApiService = ApiAdapter.getApiAdapter().create(ApiService::class.java)
            val call: Response<Logout> = apiService.logout()

            if (call.isSuccessful) {
                runOnUiThread {
                    preferences.logout()
                    finish()
                }

            } else {

                val view = layoutInflater.inflate(R.layout.toast_error, null)
                val text: TextView = view.findViewById(R.id.toastMessage)

                runOnUiThread{
                    Toast(this@HomeActivity).apply {
                        setGravity(Gravity.BOTTOM, 0, 0)
                        setView(view)
                        text.text = call.errorBody()?.string()
                    }.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

