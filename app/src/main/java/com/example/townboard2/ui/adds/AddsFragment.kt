package com.example.townboard2.ui.adds

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.townboard2.R
import com.example.townboard2.databinding.FragmentAddsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddsFragment : Fragment() {

    private var _binding: FragmentAddsBinding? = null

    private val binding get() = _binding!!
    private lateinit var addAdapter: AddAdapter
    private lateinit var addList: ArrayList<Add>
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cityName = getActivity()?.getIntent()?.getExtras()?.getString("cityName");

        auth = FirebaseAuth.getInstance()
        addList = arrayListOf<Add>()
        addAdapter = AddAdapter(context, addList)
        binding.addsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.addsRecyclerView.adapter = addAdapter

        db.collection("city").document(cityName!!)
            .collection("adds").get().addOnSuccessListener { result ->
                for (document in result.documents) {
                    val name = document.data?.let { it["name"] as String? }
                    val local = document.data?.let { it["local"] as String? }
                    val description = document.data?.let { it["description"] as String? }
                    val photoName = document.data?.let { it["photoName"] as String? }
                    addList.add(Add(photoName, name, local, description))
                }
                addAdapter.notifyDataSetChanged()
            }.addOnFailureListener() { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }


        binding.addAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_event_to_AddEventFragment)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}