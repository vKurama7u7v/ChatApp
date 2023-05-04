package com.example.chatapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chatapp.fragments.Calls
import com.example.chatapp.fragments.Chats
import com.example.chatapp.fragments.Groups

class CollectionAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

     override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Chats()
            1 -> Groups()
            2 -> Calls()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}