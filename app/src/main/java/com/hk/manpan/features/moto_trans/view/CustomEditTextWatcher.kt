package com.hk.manpan.features.moto_trans.view

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText

open class CustomEditTextWatcher(
    private val editText: EditText,
    private val formatChar: String,
    private val posSuffixes: List<Int>
) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val selectionStart: Int = editText.selectionStart
        val selectionEnd: Int = editText.selectionEnd
        val length: Int = s?.length!!
        Log.d(
            TAG,
            " selectionStart: $selectionStart - selectionEnd: $selectionEnd " +
                    "- length : $length - before: $before - count: $count"
        )
        if (before == 0) {
            // insert
            if (selectionStart != s.length) {
                formatInput(selectionStart)
            } else if (posSuffixes.contains(selectionStart)) {
                // append space at pos need to add space
                Log.d(TAG, "append")
                editText.append(formatChar);
            }
        } else if (before in 1 until length) {
            // DEL is pressed
            Log.d(TAG, "delete:")
            // check need to format input after delete
            val selectionStartAfterDelete = editText.selectionStart
            if (selectionStartAfterDelete != editText.text.length) {
                formatInput(selectionStartAfterDelete)
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        // do nothing
    }

    private fun formatInput(selectionStart: Int) {
        val builder: StringBuilder =
            StringBuilder(editText.text.trimStart().trimEnd().replace(Regex(formatChar), ""))

        var posSelection = selectionStart
        if (posSuffixes.contains(selectionStart)) {
            posSelection += 1
        }

        Log.d(TAG, "format $builder")
        for (pos in posSuffixes) {
            if (pos < builder.length) {
                Log.d(TAG, "insert at $pos")
                builder.insert(pos, formatChar)
            }
        }
        editText.removeTextChangedListener(this)
        editText.setText(builder.toString())
        editText.setSelection(posSelection)
        editText.addTextChangedListener(this)
    }

    companion object {
        private const val TAG = "CardTextWatcher"
    }
}