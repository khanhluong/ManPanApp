package com.hk.manpan.features.moto_trans.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hk.manpan.R
import com.hk.manpan.data.local.entity.CardEntryEntity
import com.hk.manpan.databinding.FragmentMotoTransBinding
import com.hk.manpan.features.moto_trans.viewmodel.MotoTransViewModel
import com.hk.manpan.utils.Constants.FORMAT_CHAR
import com.hk.manpan.utils.Constants.FORMAT_POS_LIST
import com.hk.manpan.utils.Constants.POS_SPACES
import com.hk.manpan.utils.Constants.SPACE_CHAR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MotoTransFragment : Fragment(R.layout.fragment_moto_trans) {

    private val motoTransViewModel: MotoTransViewModel by viewModels()
    private lateinit var motoTransBinding: FragmentMotoTransBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        motoTransBinding = FragmentMotoTransBinding.inflate(layoutInflater)
        return motoTransBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // add TextWatcher to format display for card
        motoTransBinding.cardEntryEdEnterCard.addTextChangedListener(
            CustomEditTextWatcher(
                motoTransBinding.cardEntryEdEnterCard,
                SPACE_CHAR,
                POS_SPACES
            )
        )
        // custom password transformation to hide the card number
        motoTransBinding.cardEntryEdEnterCard.transformationMethod =
            CustomPasswordTransformationMethod()

        // add TextWatcher to format display expiry date
        motoTransBinding.cardEntryEdExpiryDate.addTextChangedListener(
            CustomEditTextWatcher(
                motoTransBinding.cardEntryEdExpiryDate,
                FORMAT_CHAR,
                FORMAT_POS_LIST
            )
        )

        motoTransBinding.cardEntrySpinnerMotoType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position).toString()
                if(selectedItem == "SINGLE MOTO"){
                    motoTransBinding.cardEntryTvStoredCredentials.visibility = View.GONE
                    motoTransBinding.cardEntryTvCardStoredOnFile.visibility = View.GONE
                    motoTransBinding.cardEntrySwitchStoredOnFile.visibility = View.GONE
                }else {
                    motoTransBinding.cardEntryTvStoredCredentials.visibility = View.VISIBLE
                    motoTransBinding.cardEntryTvCardStoredOnFile.visibility = View.VISIBLE
                    motoTransBinding.cardEntrySwitchStoredOnFile.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //NOT DOING
            }

        }

        motoTransBinding.cardEntryBtnContinue.setOnClickListener {
            Log.d("Test", "onclick button")
            val pan = motoTransBinding.cardEntryEdEnterCard.text.toString()
            val expiry = motoTransBinding.cardEntryEdExpiryDate.text.toString()
            val cvv = motoTransBinding.cardEntryEdSecurityCode.text.toString()
            val motoType = motoTransBinding.cardEntrySpinnerMotoType.selectedItem.toString() == "SINGLE MOTO"
            val isCardStoreOnFile = motoTransBinding.cardEntrySwitchStoredOnFile.isChecked
            //currently not have value
            val isNoCVVReason = ""
            val amount = motoTransBinding.cardEntryTvAmount.text.toString()
            val manPanEntity = CardEntryEntity(
                pan = pan,
                cvv = cvv,
                expiry = expiry,
                motoType = motoType,
                isCardStoreOnFile = isCardStoreOnFile,
                noCVVReason = isNoCVVReason,
                amount = 200.0
            )
            Log.d(TAG, manPanEntity.toString())
            motoTransViewModel.insertCardEntry(manPanEntity).observe(viewLifecycleOwner, {
                if (!it) {
                    Toast.makeText(context, "Input error!!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "DONE!!!", Toast.LENGTH_SHORT).show()
                }
            })

            motoTransViewModel.getNumberOfEntryCard().observe(viewLifecycleOwner, {
                Log.d("Test", "Size of " + it.size.toString())
            })
        }
    }

    companion object {
        val TAG = MotoTransFragment::class.simpleName
    }
}
