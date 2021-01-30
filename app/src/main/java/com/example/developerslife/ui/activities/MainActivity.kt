package com.example.developerslife.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.developerslife.R
import com.example.developerslife.ui.fragments.TabFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, TabFragment())
                .commit()
        }
    }

}