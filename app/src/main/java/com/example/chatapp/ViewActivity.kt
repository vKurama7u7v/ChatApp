package com.example.chatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.chatapp.adapters.CollectionAdapter
import com.example.chatapp.databinding.ActivityViewBinding
import com.google.android.material.tabs.TabLayout

class ViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewBinding

    var tabs: TabLayout? = null
    var viewPager: ViewPager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabs = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)


        tabs!!.addTab(tabs!!.newTab().setText("Chats"))
        tabs!!.addTab(tabs!!.newTab().setText("Groups"))
        tabs!!.addTab(tabs!!.newTab().setText("Calls"))
        tabs!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = CollectionAdapter(supportFragmentManager, lifecycle)
        viewPager!!.adapter = adapter

        tabs!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
    }


}