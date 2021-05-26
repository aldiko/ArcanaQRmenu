package com.example.arcanaqrmenu.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.arcanaqrmenu.R
import com.example.arcanaqrmenu.databinding.SingleActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.single__activity.*

class SingleActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()

        setContentView(R.layout.single__activity)
        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNav,navController)
        //NavigationUI.setupActionBarWithNavController(this,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    private fun showBottomNav() {
        bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
       bottomNav.visibility = View.GONE
    }


}