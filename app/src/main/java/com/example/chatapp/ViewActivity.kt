package com.example.chatapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.chatapp.adapters.CollectionAdapter
import com.example.chatapp.adapters.PagerAdapter
import com.example.chatapp.databinding.ActivityViewBinding
import com.google.android.material.tabs.TabLayout

class ViewActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityViewBinding
    private lateinit var myViewPager : ViewPager
    private lateinit var pvAdapter: PagerAdapter

    // Buttons
    private lateinit var btnChats: ImageView
    private lateinit var btnFriends: ImageView
    private lateinit var btnSettings: ImageView

    /*
    var tabs: TabLayout? = null
    var viewPager: ViewPager2? = null
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Vistas
        inicializarVistas()

        // Asignar Eventos
        asignarEventos()

        // Adapter
        pvAdapter = PagerAdapter(supportFragmentManager)
        myViewPager.adapter = pvAdapter
        myViewPager.offscreenPageLimit = 3 // <- Cambiar el limite de las tabs

        myViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //TODO("Not yet implemented")
            }

            override fun onPageSelected(position: Int) {
                //TODO("Not yet implemented")
                onChangeTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                //TODO("Not yet implemented")
            }
        })

        myViewPager.currentItem = 0
        btnChats.setImageResource(R.drawable.baseline_chat_bubble_outline_active)

        /*
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

         */
    }

    fun inicializarVistas(){
        myViewPager = findViewById(R.id.myViewPager)
        btnChats = findViewById(R.id.btnNavChats)
        btnFriends = findViewById(R.id.btnNavFriends)
        btnSettings = findViewById(R.id.btnNavSettings)
    }

    fun asignarEventos(){
        btnChats.setOnClickListener(this)
        btnFriends.setOnClickListener(this)
        btnSettings.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        //TODO("Not yet implemented")
        when(p0?.id){
            R.id.btnNavChats -> {
                myViewPager.currentItem = 0
            }
            R.id.btnNavFriends -> {
                myViewPager.currentItem = 1
            }
            R.id.btnNavSettings -> {
                myViewPager.currentItem = 2
            }
        }
    }

    private fun onChangeTab(position: Int) {

        // Falta cambiar color

        if (position == 0) {
            btnChats.setImageResource(R.drawable.baseline_chat_bubble_outline_active)
            btnFriends.setImageResource(R.drawable.baseline_person_outline)
            btnSettings.setImageResource(R.drawable.baseline_settings)
        }

        if (position == 1) {
            btnChats.setImageResource(R.drawable.baseline_chat_bubble_outline)
            btnFriends.setImageResource(R.drawable.baseline_person_outline_active)
            btnSettings.setImageResource(R.drawable.baseline_settings)
        }

        if (position == 2) {
            btnChats.setImageResource(R.drawable.baseline_chat_bubble_outline)
            btnFriends.setImageResource(R.drawable.baseline_person_outline)
            btnSettings.setImageResource(R.drawable.baseline_settings_active)
        }
    }

}