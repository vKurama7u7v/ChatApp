package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.activities.ChatActivity
import com.example.chatapp.adapters.ChatAdapter
import com.example.chatapp.models.Chat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ListOfChats : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnNavChats: ImageView
    private lateinit var btnNavFriends: ImageView
    private lateinit var btnNavSettings: ImageView

    private lateinit var btnNewChat: ImageButton
    private lateinit var etNewChat: EditText

    private lateinit var listChatsRecyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager

    private var user = ""
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_chats)

        intent.getStringExtra("user")?.let { user = it }
        if (user.isNotEmpty()){
            Log.d("DATOS:", user)

            initViews()
            asignarEventos()
        }
    }

    private fun initViews(){
        btnNavChats = findViewById(R.id.btnNavChats)
        btnNavFriends = findViewById(R.id.btnNavFriends)
        btnNavSettings = findViewById(R.id.btnNavSettings)

        btnNewChat = findViewById(R.id.newChatButton)
        etNewChat = findViewById(R.id.newChatText)

        listChatsRecyclerView = findViewById(R.id.listChatsRecyclerView)
        listChatsRecyclerView.layoutManager = LinearLayoutManager(this)
        listChatsRecyclerView.adapter = ChatAdapter { chat -> chatSelected(chat) }

        val userRef = db.collection("users").document(user)

        userRef.collection("chats")
            .get()
            .addOnSuccessListener { chats ->
                val listChats = chats.toObjects(Chat::class.java)

                (listChatsRecyclerView.adapter as ChatAdapter).setData(listChats)
            }

        userRef.collection("chats")
            .addSnapshotListener { chats, error ->
                if(error == null){
                    chats?.let {
                        val listChats = it.toObjects(Chat::class.java)
                        (listChatsRecyclerView.adapter as ChatAdapter).setData(listChats)
                    }
                }
            }
    }

    private fun asignarEventos(){
        btnNavChats.setOnClickListener(this)
        btnNavFriends.setOnClickListener(this)
        btnNavSettings.setOnClickListener(this)
        btnNewChat.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        //TODO("Not yet implemented")
        when(p0?.id){
            R.id.newChatButton -> {
                newChat()
            }
        }
    }

    private fun chatSelected(chat: Chat){
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("chatId", chat.id)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun newChat(){
        val chatId = UUID.randomUUID().toString()
        val otherUser = etNewChat.text.toString()
        val users = listOf(user, otherUser)

        val chat = Chat(
            id = chatId,
            name = "Chat con $otherUser",
            users = users
        )

        db.collection("chats").document(chatId).set(chat)
        db.collection("users").document(user).collection("chats").document(chatId).set(chat)
        db.collection("users").document(otherUser).collection("chats").document(chatId).set(chat)

        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("chatId", chatId)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}