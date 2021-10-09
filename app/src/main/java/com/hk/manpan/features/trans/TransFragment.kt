package com.hk.manpan.features.trans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hk.manpan.R
import com.hk.manpan.databinding.FragmentTransBinding

class TransFragment : Fragment(R.layout.fragment_trans) {


    lateinit var binding: FragmentTransBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPay.setOnClickListener{
            findNavController().navigate(
                R.id.action_transFragment_to_motoTransFragment
            )
        }
    }
}