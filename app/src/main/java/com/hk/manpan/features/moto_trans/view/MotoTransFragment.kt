package com.hk.manpan.features.moto_trans.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hk.manpan.R
import com.hk.manpan.databinding.FragmentMotoTransBinding

class MotoTransFragment : Fragment(R.layout.fragment_moto_trans){
    private lateinit var motoTransBinding: FragmentMotoTransBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        motoTransBinding = FragmentMotoTransBinding.inflate(layoutInflater);
        return motoTransBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        motoTransBinding.cardEntryEdEnterCard.addTextChangedListener(CardTextWatcher())
        motoTransBinding.cardEntryBtnContinue.setOnClickListener{
            Log.d("Test", "onclick button")
        }
    }
}