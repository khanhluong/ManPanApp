package com.hk.manpan.features.moto_trans.view

import android.text.Editable
import android.text.TextWatcher

class CardTextWatcher : TextWatcher {
    private val space : Char = ' ';
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        // Remove all spacing char
        var pos  = 0
        while (true) {
            if (pos >= s!!.length) break;
            if (space == s[pos] && (((pos + 1) % 5) != 0 || pos + 1 == s.length)) {
                s.delete(pos, pos + 1);
            } else {
                pos++
            }
        }

        // Insert char where needed.
        pos = 4
        while (true) {
            if (pos >= s.length) break
            val c : Char = s[pos]
            // Only if its a digit where there should be a space we insert a space
            if ("0123456789".indexOf(c) >= 0) {
                s.insert(pos, "" + space)
            }
            pos += 5
        }
    }
}