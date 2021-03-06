package org.ak80.skeleton.entry

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import org.ak80.skeleton.R
import org.ak80.skeleton.info.InfoActivity
import org.ak80.skeleton.util.ActivityUtils
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * View for showing the main screen with the list of [org.ak80.skeleton.model.Entry]s
 */
class EntryActivity : DaggerAppCompatActivity(), AnkoLogger {

    @Inject
    lateinit var entryPresenter: EntryPresenter
    @Inject
    lateinit var entryFragmentProvider: Lazy<EntryFragment>

    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_act)

        setupToolbar()
        setupNavDrawer()
        setupFragment()

        if (savedInstanceState != null) {
            info("${this.localClassName} instance state was saved")
        }
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupNavDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout?.setStatusBarBackground(R.color.colorPrimaryDark)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView != null) {
            setupDrawerContent(navigationView)
        }
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.list_navigation_menu_item -> {
                    // do nothing, we're already on that screen
                }
                R.id.info_navigation_menu_item -> {
                    startActivity(intentFor<InfoActivity>())
                }
                else -> {
                    // ignore
                }
            }
            menuItem.isChecked = true
            drawerLayout?.closeDrawers()
            true
        }
    }

    private fun setupFragment() {
        ActivityUtils.loadFragment(R.id.contentFrame, entryFragmentProvider, supportFragmentManager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                openNavigationDrawer()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openNavigationDrawer() {
        drawerLayout?.openDrawer(GravityCompat.START)
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}