package com.venkatesh.dynamiclistdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.venkatesh.dynamiclistdemo.items.view.ItemListingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, ItemListingFragment.newInstance(),ItemListingFragment::class.java.name.toString()).addToBackStack(ItemListingFragment::class.java.name.toString()).commit()
    }
}
