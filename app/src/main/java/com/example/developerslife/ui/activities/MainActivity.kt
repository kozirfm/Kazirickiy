package com.example.developerslife.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.developerslife.R
import com.example.developerslife.ui.fragments.TabFragment
import org.koin.androidx.fragment.android.replace
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI

@KoinExperimentalAPI
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace<TabFragment>(R.id.fragmentContainerView)
                .commit()
        }
    }

}