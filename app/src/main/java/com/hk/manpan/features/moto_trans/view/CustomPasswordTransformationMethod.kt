package com.hk.manpan.features.moto_trans.view

import android.text.method.PasswordTransformationMethod
import android.view.View
import com.hk.manpan.utils.Constants

class CustomPasswordTransformationMethod : PasswordTransformationMethod() {
    override fun getTransformation(source: CharSequence?, view: View?): CharSequence {
        return CustomPasswordCharSequence(source!!)
    }

    class CustomPasswordCharSequence(private val charSequence: CharSequence) : CharSequence {
        override val length: Int
            get() = charSequence.length

        override fun get(index: Int): Char {
            return when {
                Constants.POS_SPACES.contains(index) -> {
                    Constants.SPACE_CHAR.toCharArray()[0]
                }
                index > Constants.POS_SPACES[0] && index <= Constants.POS_SPACES[Constants.POS_SPACES.size - 1] -> {
                    Constants.MASK_CHAR
                }
                else -> {
                    charSequence[index]
                }
            }
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return charSequence.subSequence(startIndex, endIndex)
        }
    }
}