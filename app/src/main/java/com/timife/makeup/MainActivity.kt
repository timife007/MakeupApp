package com.timife.makeup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.loader.content.CursorLoader
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.timife.makeup.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            this.splashScreen.setSplashScreenTheme()
//        }else{
//
//        }
    }

    override fun onSupportNavigateUp()= NavigationUI.navigateUp(navController,null)


}