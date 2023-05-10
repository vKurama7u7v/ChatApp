package com.example.chatapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.chatapp.fragments.Chats
import com.example.chatapp.fragments.Friends
import com.example.chatapp.fragments.Settings

internal class PagerAdapter (fm: FragmentManager?): FragmentPagerAdapter(fm!!){
    override fun getItem(position: Int): Fragment {
        //TODO("Not yet implemented")
        return when(position) {
            0 -> {
                Chats()
            }

            1 -> {
                Friends()
            }

            2 -> {
                Settings()
            }

            else -> Chats()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
