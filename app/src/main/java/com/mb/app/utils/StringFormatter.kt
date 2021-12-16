package com.mb.app.utils

class StringFormatter {

    companion object {

        fun formatCurrency(sign: String, value: String): String {
            return sign + value
        }

        @JvmName("formatCurrency1")
        fun formatCurrency(sign: String, value: String?): String {
            return if (value.isNullOrBlank()) "N/A"
            else sign + value
        }
    }
}