package com.example.townboard2.ui.adds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.townboard2.R
import com.example.townboard2.databinding.FragmentAddDetailBinding
import com.example.townboard2.databinding.FragmentAddsBinding

class AddDetailFragment : Fragment() {

    private var _binding: FragmentAddDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    //get stuff from intent? or from database?
    //just set text and do popbackstack on navigation
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addsdescriptionDetail
    }


}