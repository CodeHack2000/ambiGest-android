package com.example.ambigest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentContainerView = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHost
        navController = fragmentContainerView.navController

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun showMenuItems() {
        val menu = findViewById<MaterialToolbar>(R.id.my_toolbar).menu
        menu.findItem(R.id.menuItem_user).isVisible = true
        menu.findItem(R.id.menuItem_contacts).isVisible = true
        menu.findItem(R.id.menuItem_logout).isVisible = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItem_user -> {
                true
            }
            R.id.menuItem_contacts -> {
                true
            }
            R.id.menuItem_logout -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        // Do nothing
    }

    override fun onSupportNavigateUp(): Boolean {
        return false
    }
}