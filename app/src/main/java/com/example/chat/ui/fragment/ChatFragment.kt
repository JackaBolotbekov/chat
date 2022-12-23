package com.example.chat.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.databinding.FragmentChatBinding
import com.example.chat.ui.adapter.RecyclerAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val chatAdapter = RecyclerAdapter()
    //Создем перменную чтобы работать с нашим Firestore
    private val firebase = Firebase.firestore

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        getCollection()
        initialize()
    }

    private fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun setUpListener() {
        binding.fabAdd.setOnClickListener {
            // Создаем нашу колекцию
            val collectionRef = firebase.collection("Jaka")
            val message = hashMapOf(
                // Возвращает новый HashMap с указанным содержимым в виде списка пар,
                // где первый компонент является ключом, а второй — значением
                "message" to binding.etText.text.toString()
            )
            // проверяет успешность тправки сообщения
            collectionRef.add(message).addOnSuccessListener {
            }
        }
    }

    private fun getCollection() {
        val collection = firebase.collection("Jaka")
            // Получаем обновления в реальном времени с помощью Cloud Firestore
        collection.addSnapshotListener { value, _ ->
            if (value != null) {
                // Создаем наш лист из String значений
                val list = ArrayList<String>()
                for (string in value) {
                    string.getString("message")?.let {
                        list.add(it).toString().trim() // trim() чтобы не отправлять пустату
                    }
                }
                chatAdapter.setData(list)
            }
        }
    }
}