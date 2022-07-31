package com.rubelz.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.rubelz.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var bind: ActivityMainBinding? = null

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind?.root)

        supportActionBar?.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHost
        navController = navHostFragment.navController

        // Restore screen when config. changes
        navController?.restoreState(savedInstanceState?.getBundle(NAV_STATE))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(NAV_STATE, navController?.saveState())
    }

    companion object {
        const val NAV_STATE = "NAV_STATE"
    }

}