package com.example.chatapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.fragments.Chats
import com.example.chatapp.models.Chat

class ChatAdapter(val chatClick: (Chat) -> Unit): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    var chats: List<Chat> = emptyList()

    fun setData(list: List<Chat>){
        chats = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_chat,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.chatNameText)?.text = chats[position].name
        holder.itemView.findViewById<TextView>(R.id.usersTextView)?.text = chats[position].users.toString()

        holder.itemView.setOnClickListener {
            chatClick(chats[position])
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
/*
class ChatAdapter(chats: ArrayList<Chat>, context: Context): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>(){
    var innerChats: ArrayList<Chat> = chats
    var innerContext: Context = context

    inner class ChatViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val chatNameText: TextView
        val usersTextView: TextView

        init{
            // Define click listener for the ViewHolder´s View
            chatNameText = view.findViewById(R.id.chatNameText)
            usersTextView = view.findViewById(R.id.usersTextView)
        }

        override fun onClick(p0: View?) {
            //TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        //TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return innerChats.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        //TODO("Not yet implemented")

        val chat: Chat = innerChats.get(position)

        holder.chatNameText.text = chat.name
        holder.usersTextView.text = chat.users.toString()
    }

}
*/