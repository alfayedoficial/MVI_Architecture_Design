package com.alialfayed.mviarchitecturedesign.feature.main

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alialfayed.mviarchitecturedesign.R
import com.alialfayed.mviarchitecturedesign.core.base.view.BaseActivity
import com.alialfayed.mviarchitecturedesign.core.utils.hide
import com.alialfayed.mviarchitecturedesign.core.utils.show
import com.alialfayed.mviarchitecturedesign.databinding.ActivityOneSingleBinding

class OneSingleActivity(override val layoutResourceId: Int = R.layout.activity_one_single) : BaseActivity<ActivityOneSingleBinding>() {

    var backPressed = 0L
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(dataBinder: ActivityOneSingleBinding) {
        print(dataBinder.toString())
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        dataBinder.apply {
            bottomNavigationView.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment ->{
//                        val navOptions = NavOptions.Builder()
//                            .setPopUpTo(R.id.getStartedFragment, true)
//                            .build()
//                        navController.navigate(R.id.action_getStartedFragment_to_homeFragment, null, navOptions);

//                        val navGraph = navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph)
//                        navGraph.startDestination = R.id.homeFragment
//                        navController.graph = navGraph
                        bottomNavigationView.show()
                    }
                     R.id.wishListFragment , R.id.infoFragment -> {
                        bottomNavigationView.show()
                    }
                    else -> {
                        bottomNavigationView.hide()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}