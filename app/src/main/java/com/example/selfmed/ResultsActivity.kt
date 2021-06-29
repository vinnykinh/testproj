package com.example.selfmed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.Fragment

import com.example.selfmed.fragments.fragment_1
import com.example.selfmed.fragments.fragment_3
import com.google.android.material.bottomnavigation.BottomNavigationView

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val toolBar = findViewById<Toolbar>(R.id.toolBar_results)
        setSupportActionBar(toolBar)
        supportActionBar!!.title = "Results"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener {
            finish()
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener(onItemSelectedListener)
        moveToFragment(fragment_1())


    }

    private val onItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val itemId = item.itemId
            when (itemId) {
                R.id.results -> {
                    moveToFragment(fragment_1())

                    return@OnNavigationItemSelectedListener true
                }
                R.id.Location -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.learnmore_res -> {
                    moveToFragment(fragment_3())

                    return@OnNavigationItemSelectedListener true

                }
                else -> {
                    return@OnNavigationItemSelectedListener false
                }
            }
        }

    private fun moveToFragment(fragment: Fragment?) {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.frameLayout, fragment!!).commit()
    }


}