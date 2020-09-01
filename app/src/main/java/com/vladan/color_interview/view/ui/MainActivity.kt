package com.vladan.color_interview.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vladan.color_interview.R

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, listFragment).commit()
        }
    }

    fun show(id: String?) {
        val personFragment = PersonFragment.forPerson(id)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("person")
            .replace(R.id.container, personFragment, null).commit()
    }

    override fun onBackPressed() {

        super.onBackPressed()
    }
}