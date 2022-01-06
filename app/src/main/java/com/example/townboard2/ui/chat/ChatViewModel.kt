package com.example.townboard2.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}

//class ChatFragment : Fragment() {
//
//    private lateinit var binding: FragmentChatBinding
//    private lateinit var messageAdapter: MessageAdapter
//    private lateinit var messageList: ArrayList<Message>
//    private lateinit var auth: FirebaseAuth
//    private lateinit var mContext: Context
//    val db = Firebase.firestore
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mContext = context
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        binding = FragmentChatBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val intent = super.getActivity()?.intent
//        val cityName = intent?.getStringExtra("cityName")
//        auth = FirebaseAuth.getInstance()
//        messageList = arrayListOf<Message>()
//
//        messageAdapter = MessageAdapter(mContext, messageList)
//        binding.chatRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.chatRecyclerView.adapter = messageAdapter
//
//        db.collection("city").document(cityName!!)
//            .collection("messages").get()
//            .addOnSuccessListener { result ->
//                for (document in result.documents) {
//                    val message = document.data?.let { it["message"] as String? }
//                    val messageUID = document.data?.let { it["senderUID"] as String? }
//                    messageList.add(Message(message!!, messageUID!!))
//                }
//                messageAdapter.notifyDataSetChanged()
//            }.addOnFailureListener() { exception ->
//                Log.w(ContentValues.TAG, "Error getting documents.", exception)
//            }
//
//
//
//        binding.sendButton.setOnClickListener {
//            val message = binding.messageBox.text.toString()
//            val messageObj = Message(message, auth.currentUser?.uid.toString())
//            db.collection("city").document(cityName!!)
//                .collection("messages").add(messageObj)
//            messageList.add(messageObj)
//            messageAdapter.notifyDataSetChanged()
//            binding.messageBox.setText("")
//        }
//    }
//
//}