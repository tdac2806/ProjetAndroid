package com.example.projetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.projetandroid.databinding.ActivityMainBinding
import com.example.projetandroid.db.Cat
import com.example.projetandroid.db.User
import com.example.projetandroid.db.GameDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    lateinit var binding: ActivityMainBinding
    lateinit var db: GameDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        db = Room.databaseBuilder(applicationContext, GameDatabase::class.java, "GameDatabase").build()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}