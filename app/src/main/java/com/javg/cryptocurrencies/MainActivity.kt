package com.javg.cryptocurrencies

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.javg.cryptocurrencies.view.base.CRYBaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Juan Antonio Vera
 * Date modified 22/02/2023
 *
 * Contains the functionality and configuration to start
 * the application and its navigation
 *
 * @since 2.0
 */
@AndroidEntryPoint
class MainActivity : CRYBaseActivity() {

    private lateinit var navController: NavController
    private lateinit var navHostFragment : NavHostFragment
    private lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Cryptocurrencies)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cry_nav_host_main)
        setUpNavHost()
    }

    private fun setUpNavHost(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navGraph = navHostFragment.navController.navInflater.inflate(R.navigation.cry_nav_graph_main)
        navController = navHostFragment.navController
        navController.graph = navGraph
    }

}
