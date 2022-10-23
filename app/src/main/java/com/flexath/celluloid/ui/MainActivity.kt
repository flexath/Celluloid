package com.flexath.celluloid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.flexath.celluloid.R
import com.flexath.celluloid.ui.movie.PopularFirstFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var popularFirstFragment: PopularFirstFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        popularFirstFragment = PopularFirstFragment()

        getBottomNavigationItems()
    }

    private fun getBottomNavigationItems() {
        setCurrentFragment(popularFirstFragment)
        bottomNvgView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottomMovie -> {
                    setCurrentFragment(popularFirstFragment)
                }
                R.id.bottomTvShow -> {
                    Toast.makeText(this,"Tv Show clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.bottomPeople -> {
                    Toast.makeText(this,"People clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.bottomSaved -> {
                    Toast.makeText(this,"Saved clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.bottomProfile -> {
                    Toast.makeText(this,"Profile clicked",Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,fragment)
            .commit()
    }
}