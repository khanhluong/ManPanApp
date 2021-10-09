package com.hk.manpan.features.moto_trans.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hk.manpan.R
import com.hk.manpan.databinding.FragmentMotoTransBinding
import com.hk.manpan.utils.Constants.FORMAT_CHAR
import com.hk.manpan.utils.Constants.FORMAT_POS_LIST
import com.hk.manpan.utils.Constants.POS_SPACES
import com.hk.manpan.utils.Constants.SPACE_CHAR

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

        // add TextWatcher to format display for card
        motoTransBinding.cardEntryEdEnterCard.addTextChangedListener(CustomEditTextWatcher(motoTransBinding.cardEntryEdEnterCard, SPACE_CHAR, POS_SPACES))
        // custom password transformation to hide the card number
        motoTransBinding.cardEntryEdEnterCard.transformationMethod = CustomPasswordTransformationMethod()

        // add TextWatcher to format display expiry date
        motoTransBinding.cardEntryEdExpiryDate.addTextChangedListener(CustomEditTextWatcher(motoTransBinding.cardEntryEdExpiryDate, FORMAT_CHAR, FORMAT_POS_LIST))
        motoTransBinding.cardEntryBtnContinue.setOnClickListener{
            Log.d("Test", "onclick button")
        }
    }
}