package com.hk.manpan.utils

object Constants {
    // card
    const val MASK_CHAR: Char = '*'
    const val SPACE_CHAR: String = " "
    val POS_SPACES = listOf(4, 9, 14)

    // expiry
    const val FORMAT_CHAR: String = "/"
    val FORMAT_POS_LIST = listOf(2)

    const val TABLE_CARD_ENTRY = "card_entry_table"
}

data class ManPanEntityStatus(var panStatus: Boolean = true, var expiryStatus: Boolean = true)

